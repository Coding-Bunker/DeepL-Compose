package it.github.samuele794.composedeepl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme
import it.github.samuele794.composedeepl.ui.translate.TranslationPage
import it.github.samuele794.composedeepl.viewmodel.TranslationViewModel
import it.github.samuele794.composedeepl.viewmodel.TranslationViewModelImpl

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val translationViewModel: TranslationViewModel by viewModels<TranslationViewModelImpl>()
            translationViewModel.getLanguages()
            ComposeDeepLTheme {
                // A surface container using the 'background' color from the theme
                TranslationPage(translationViewModel)
            }
        }
    }
}