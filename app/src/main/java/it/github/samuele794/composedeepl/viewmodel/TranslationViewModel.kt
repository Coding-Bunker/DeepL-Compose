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

@HiltViewModel
class TranslationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    repository: DeepLRepository
) : ViewModel() {
    var translationState: TranslationState by mutableStateOf(
        savedStateHandle["translationState"] ?: TranslationState()
    )
        private set

    fun startTranslation(text: String) {
        translationState = translationState.copy(translationText = text)
        if (translationState.translationText.isNotBlank()) {
            viewModelScope.coroutineContext.cancelChildren()
            Log.i(TranslationViewModel::class.simpleName, "Translation Request Launch")
            viewModelScope.launch(Dispatchers.IO) {
                Log.i(TranslationViewModel::class.simpleName, "Translation Request Delay")
                delay(DIGITATION_DELAY)
                ensureActive()
                Log.i(TranslationViewModel::class.simpleName, "Translation Request Started")
                translationState = translationState.copy(loading = true)
                delay(DIGITATION_DELAY)
                translationState = translationState.copy(loading = false)

            }
        }
    }

    companion object {
        private const val DIGITATION_DELAY = 1000L
    }
}