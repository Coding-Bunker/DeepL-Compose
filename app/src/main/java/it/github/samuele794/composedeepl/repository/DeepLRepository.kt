package it.github.samuele794.composedeepl.repository

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import it.github.samuele794.composedeepl.BuildConfig

class DeepLRepository {
    private val httpCLient = HttpClient(CIO){
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        install(UserAgent){
            agent = BuildConfig.APPLICATION_ID
        }
        install(Logging)
        HttpRequestPipeline

        defaultRequest {
            host = BuildConfig.DEEPL_BASE_URL
            contentType(ContentType.Application.FormUrlEncoded)
            url {
                parameters.append("auth_key", BuildConfig.DEEPL_API_KEY)
            }
        }
    }.apply {
        
    }
}