package it.github.samuele794.composedeepl.model


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable(with = LanguageItemSerializer::class)
//class Language : ArrayList<LanguageItem>()

@Keep
@Serializable
@Parcelize
data class LanguageItem(
    @SerialName("language")
    val language: String,
    @SerialName("name")
    val name: String,
    @SerialName("supports_formality")
    val supportsFormality: Boolean
) : Parcelable

//object LanguageItemSerializer : JsonTransformingSerializer<List<LanguageItem>>(ListSerializer(LanguageItem.serializer())) {
//    // If response is not an array, then it is a single object that should be wrapped into the array
//    override fun transformDeserialize(element: JsonElement): JsonElement =
//        if (element !is JsonArray) JsonArray(listOf(element)) else element
//}