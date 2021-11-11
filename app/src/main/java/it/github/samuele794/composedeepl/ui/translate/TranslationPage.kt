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
import it.github.samuele794.composedeepl.ui.theme.ComposeDeepLTheme
import it.github.samuele794.composedeepl.viewmodel.TranslationState
import it.github.samuele794.composedeepl.viewmodel.TranslationViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TranslationPage(viewModel: TranslationViewModel) {
    Scaffold(topBar = {
        TranslateHeader()
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val translationText = viewModel.translationState.translationText
            TranslationComponent(
                translationText = translationText,
                reproduceEnabled = false,
                onTranslationChanged = {
                    viewModel.startTranslation(it)
                }, reproduceClicked = {

                })

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
        TranslationPage(viewModel = object : TranslationViewModel {
            override var translationState: TranslationState = TranslationState()
            override fun startTranslation(text: String) = Unit

            override fun getLanguages() = Unit
        })
    }
}