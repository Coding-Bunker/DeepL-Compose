package it.github.samuele794.composedeepl.ui.translate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme
import it.github.samuele794.composedeepl.viewmodel.TranslationViewModel

@ExperimentalAnimationApi
@Composable
fun TranslationPage(viewModel: TranslationViewModel = viewModel()) {
    Scaffold(topBar = {
        TranslateHeader()
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val translationText = viewModel.translationState.translationText
            TranslationComponent(translationText) {
                viewModel.startTranslation(it)
            }

            AnimatedVisibility(
                visible = viewModel.translationState.loading,
                Modifier.padding(top = 8.dp)
            ) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            Spacer(Modifier.height(16.dp))
            TranslationResultComponent(textResult = "")
        }
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun TranslationPagePreview() {
    ComposeDeepLTheme(darkTheme = true) {
        TranslationPage()
    }
}