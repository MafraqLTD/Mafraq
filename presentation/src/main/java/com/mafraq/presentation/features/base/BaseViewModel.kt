package com.mafraq.presentation.features.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altaie.prettycode.core.exceptions.GpsProviderIsDisabledException
import com.altaie.prettycode.core.exceptions.ResponseException
import com.altaie.prettycode.core.exceptions.ValidationException
import com.mafraq.data.entities.ApiResponse
import com.mafraq.data.errors.ConnectionException
import com.mafraq.data.errors.EmptyBodyException
import com.mafraq.data.errors.InternetDisconnectedException
import com.mafraq.data.errors.TimeoutException
import com.mafraq.data.errors.UnAuthorizedException
import com.mafraq.data.utils.errorMessage
import com.mafraq.data.utils.getValueOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import org.apache.http.HttpStatus
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class BaseViewModel<S, E>(initState: S) : ViewModel() {

    private val _state = MutableStateFlow(initState)
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<E?>()
    val event = _event

    protected fun <T> tryToExecute(
        block: suspend () -> T,
        onSuccess: (T) -> Unit = {},
        onError: (ErrorState) -> Unit = {},
        checkSuccess: (T) -> Boolean = { true },
        onCompleted: () -> Unit = {},
        inScope: CoroutineScope = viewModelScope
    ): Job {
        return inScope.launch(Dispatchers.IO) {
            runCatching { block() }
                .onSuccess { response ->
                    if (checkSuccess(response)) {
                        onSuccess(response)
                        return@onSuccess
                    }

                    if (response is ApiResponse)
                        onError(ErrorState.RequestFailed(response.message.also(Timber::d)))
                    else
                        onError(ErrorState.RequestFailed())
                }
                .onFailure { mapExceptionToErrorState(throwable = it, onError = onError) }
            onCompleted()
        }
    }

    protected fun <T> tryToCollect(
        block: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit = {},
        onError: (ErrorState) -> Unit = {},
        onCompleted: () -> Unit = {},
        latest: Boolean = false,
        takeWhile: (T) -> Boolean = { true },
        inScope: CoroutineScope = viewModelScope
    ): Job {
        return inScope.launch(Dispatchers.IO) {
            runCatching {
                if (latest)
                    block()
                        .takeWhile(takeWhile)
                        .collectLatest(onNewValue)
                else
                    block()
                        .takeWhile(takeWhile)
                        .collect(onNewValue)
            }
                .onFailure { mapExceptionToErrorState(throwable = it, onError = onError) }
            onCompleted()
        }
    }


    protected fun updateState(notifyEvent: E? = null, updater: S.() -> S) {
        _state.update(updater)
        emitNewEvent(notifyEvent ?: return)
    }

    protected fun emitNewEvent(newEvent: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _event.emit(newEvent)
        }
    }

    private inline fun <T> MutableStateFlow<T>.update(block: T.() -> T) {
        while (true) {
            val prevValue = value
            val nextValue = block(prevValue)
            if (compareAndSet(prevValue, nextValue))
                return
        }
    }

    private fun mapExceptionToErrorState(throwable: Throwable, onError: (ErrorState) -> Unit) {
        val message = throwable.message.getValueOf("message")

        val exception = when (throwable) {
            is ConnectException -> ConnectionException()
            is SocketTimeoutException,
            is TimeoutCancellationException -> TimeoutException()

            is UnknownHostException -> InternetDisconnectedException()
            else -> throwable
        }

        when (exception) {
            is UnAuthorizedException -> ErrorState.UnAuthorized(message)
            is ConnectionException -> ErrorState.NoInternet(exception.message)
            is InternetDisconnectedException -> ErrorState.NoInternet(exception.message)
            is EmptyBodyException -> ErrorState.EmptyBody(message)
            is TimeoutException -> ErrorState.Timeout(exception.message)
            is ValidationException -> ErrorState.Validation(exception.message)
            is GpsProviderIsDisabledException -> ErrorState.GpsProviderIsDisabled(exception.message)
            is ResponseException -> {
                when (exception.code) {
                    HttpStatus.SC_UNAUTHORIZED -> ErrorState.UnAuthorized(exception.errorMessage)
                    HttpStatus.SC_TOO_MANY_REQUESTS -> ErrorState.RequestFailed(
                        TOO_MANY_REQUESTS
                    )

                    else -> ErrorState.RequestFailed(exception.errorMessage)
                }
            }

            else -> ErrorState.RequestFailed(message).also { Timber.e(throwable) }
        }.run(onError)
    }

    private companion object{
        const val TOO_MANY_REQUESTS = "Too Many Attempts"
    }
}
