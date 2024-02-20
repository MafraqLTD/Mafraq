package com.mafraq.presentation.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mafraq.presentation.design.theme.MafraqTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    MafraqTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(text = "Hello!")
            ElevatedButton(onClick = { /*TODO*/ }) {
                Text(text = "Click Me!")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App()
}