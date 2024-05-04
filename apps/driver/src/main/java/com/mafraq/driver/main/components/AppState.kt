package com.mafraq.driver.main.components

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
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
    private val driverSubscriptionRepository: DriverSubscriptionRepository
) {
    private var scope: CoroutineScope? = null
    var hasSubscribers: Boolean by mutableStateOf(false)
        private set

    val isAuthorized: Boolean
        get() = authRepository.isAuthorized()

    val isProfileFilled: Boolean
        get() = authRepository.isProfileFilled

    fun reload() {
        scope?.cancel()
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
            driverSubscriptionRepository.subscribersFlow.collect {
                hasSubscribers = it.isNotEmpty()
            }
        }
    }

    init {
        reload()
    }
}
