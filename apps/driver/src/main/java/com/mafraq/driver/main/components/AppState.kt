package com.mafraq.driver.main.components

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.mafraq.data.entities.AppUserType
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.remote.dataSource.subscription.driver.DriverSubscriptionDataSource
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.data.repository.subscription.driver.DriverSubscriptionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


val LocalAppStateProvider: ProvidableCompositionLocal<AppState> = staticCompositionLocalOf {
    error("AppState Is Not Provided")
}


class AppState @Inject constructor(
    authRepository: AuthRepository,
    driverSubscriptionRepository: DriverSubscriptionRepository
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    var hasSubscribers: Boolean by mutableStateOf(false)
        private set

    val isAuthorized: Boolean = authRepository.isAuthorized()
    val isProfileFilled: Boolean = authRepository.isProfileFilled

    init {
        scope.launch {
            driverSubscriptionRepository.membersFlow.collect {
                hasSubscribers = it.isNotEmpty()
            }
        }
    }
}
