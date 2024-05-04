package com.mafraq.employee.main.components

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.subscription.employee.EmployeeSubscriptionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject


val LocalAppStateProvider: ProvidableCompositionLocal<AppState> = staticCompositionLocalOf {
    error("AppState Is Not Provided")
}


class AppState @Inject constructor(
    private val authRepository: AuthRepository,
    private val employeeSubscriptionRepository: EmployeeSubscriptionRepository
) {
    private var scope: CoroutineScope? = null
    var isSubscribed: Boolean by mutableStateOf(false)
        private set

    val isAuthorized: Boolean
        get() = authRepository.isAuthorized()

    val isProfileFilled: Boolean
        get() = authRepository.isProfileFilled

    fun reload() {
        scope?.cancel()
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
            employeeSubscriptionRepository.subscribeRequestStatusFlow.collect {
                isSubscribed = it.isAccepted
            }
        }
    }

    init {
        reload()
    }
}
