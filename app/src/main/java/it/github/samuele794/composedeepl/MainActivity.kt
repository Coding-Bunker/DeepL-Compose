package it.github.samuele794.composedeepl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme
import it.github.samuele794.composedeepl.ui.translate.TranslationPage

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDeepLTheme {
                // A surface container using the 'background' color from the theme
                TranslationPage()
            }
        }
    }
}