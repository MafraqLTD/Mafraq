package com.mafraq.employee.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.mafraq.data.entities.AppUserType
import com.mafraq.data.local.session.SessionLocalDataSource
import com.mafraq.data.repository.auth.AuthRepository
import com.mafraq.employee.main.components.AppState
import com.mafraq.employee.main.components.LocalAppStateProvider
import com.mafraq.presentation.design.components.navigation.LocalAppUserType
import com.mafraq.presentation.design.components.navigation.LocalNavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appUserType: AppUserType

    @Inject
    lateinit var appState: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            CompositionLocalProvider(
                LocalAppUserType provides appUserType,
                LocalAppStateProvider provides appState,
                LocalNavigationProvider provides rememberNavController()
            ) {
                App(
                    isAuthorized = appState.isAuthorized,
                    isProfileFilled = appState.isProfileFilled,
                )
            }
        }
    }
}
