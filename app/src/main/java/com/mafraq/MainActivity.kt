package com.mafraq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mafraq.presentation.design.theme.MafraqTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    MafraqTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colors.background
        ) {
            Text(text = "Hello!",)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App()
}