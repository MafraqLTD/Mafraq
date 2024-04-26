package com.mafraq.presentation.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.presentation.design.components.navigation.LocalNavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    @Inject
    lateinit var sessionLocalDataSource: SessionLocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isAuthorized = authRepository.isAuthorized()
        val isProfileFilled = authRepository.isProfileFilled
        val isSubscribed = sessionLocalDataSource.get()?.subscriptionId.isNullOrEmpty().not()

        installSplashScreen()

        setContent {
            CompositionLocalProvider(
                LocalNavigationProvider provides rememberNavController()
            ) {
                App(
                    isAuthorized = isAuthorized,
                    isProfileFilled = isProfileFilled,
                    isSubscribed = isSubscribed
                )
            }
        }
    }
}
