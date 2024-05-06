package com.mafraq.data.remote.dataSource

import com.altaie.prettycode.core.base.Resource
import com.altaie.prettycode.core.exceptions.ResponseException
import com.altaie.prettycode.core.exceptions.ValidationException
import com.google.gson.Gson
import com.mafraq.data.remote.mappers.HttpExceptionMapper
import org.apache.http.HttpStatus
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber


interface BaseRemoteDataSource {
    suspend fun <T, R> apiCall(
        suspendFunction: suspend () -> Response<T>,
        mapper: suspend (T) -> R
    ) = try {
        checkIfSuccessful(result = suspendFunction(), mapper = mapper)
    } catch (t: HttpException) {
        throw httpExceptionMapper.map(from = t)
    }

    private suspend fun <T, R> checkIfSuccessful(
        result: Response<T>,
        mapper: suspend (T) -> R
    ): Resource<R> = if (result.isSuccessful)
        result.body()?.run {
            Resource.Success(mapper(this).also { Timber.d("Response: $it") })
        } ?: Resource.Empty
    else if (result.code() == HttpStatus.SC_UNPROCESSABLE_ENTITY)
        throw runCatching {
            Gson().fromJson(
                result.errorBody()?.string(),
                ValidationException::class.java
            )
        }.onFailure {
            throw ResponseException(
                message = result.errorBody()?.string().toString(),
                code = result.code()
            )
        }.getOrThrow()
    else
        throw ResponseException(
            message = result.errorBody()?.string().toString(),
            code = result.code()
        )

    private companion object {
        val httpExceptionMapper: HttpExceptionMapper = HttpExceptionMapper()
    }
}
