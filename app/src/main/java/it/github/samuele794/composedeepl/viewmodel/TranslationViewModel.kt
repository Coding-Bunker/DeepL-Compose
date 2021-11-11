package it.github.samuele794.composedeepl.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.github.samuele794.composedeepl.repository.DeepLRepository
import kotlinx.coroutines.*
import javax.inject.Inject

data class TranslationState(
    var translationText: String = "",
    var loading: Boolean = false
)

interface TranslationViewModel {
    var translationState: TranslationState

    fun startTranslation(text: String)
    fun getLanguages()
}

@HiltViewModel
class TranslationViewModelImpl @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: DeepLRepository
) : ViewModel(), TranslationViewModel {
    override var translationState: TranslationState by mutableStateOf(
        savedStateHandle["translationState"] ?: TranslationState()
    )

    override fun startTranslation(text: String) {
        translationState = translationState.copy(translationText = text)
        if (translationState.translationText.isNotBlank()) {
            viewModelScope.coroutineContext.cancelChildren()
            translationState = translationState.copy(loading = true)
            Log.i(TranslationViewModelImpl::class.simpleName, "Translation Request Launch")
            viewModelScope.launch(Dispatchers.IO) {
                delay(DIGITATION_DELAY)
                ensureActive()
                Log.i(TranslationViewModelImpl::class.simpleName, "Translation Request Started")

            }
        }
    }

    override fun getLanguages() {
        viewModelScope.launch(Dispatchers.IO) {
            val sourceLanguage = async {
                repository.getSourceLanguages()
            }

            val targetLanguage = async {
                repository.getTargetLanguages()
            }

            kotlin.runCatching {
                sourceLanguage.await() to targetLanguage.await()
            }.onSuccess {
                it

            }.onFailure {

            }
        }
    }

    companion object {
        private const val DIGITATION_DELAY = 1000L
    }
}