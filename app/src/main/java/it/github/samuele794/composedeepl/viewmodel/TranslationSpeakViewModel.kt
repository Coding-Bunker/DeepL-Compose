package it.github.samuele794.composedeepl.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*
import javax.inject.Inject

class TranslationSpeakViewModel @Inject constructor(application: Application) : ViewModel() {
    lateinit var startLanguage: Locale

    var textToSpeechStatus: Int by mutableStateOf(TextToSpeech.ERROR)
    val textToSpeechClient = TextToSpeech(application) {
        textToSpeechStatus = it
    }

    fun startTTS(text: String) {
        val result = textToSpeechClient.setLanguage(startLanguage)
        textToSpeechClient.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            Base64.getEncoder().encodeToString(text.toByteArray())
        )

    }
}