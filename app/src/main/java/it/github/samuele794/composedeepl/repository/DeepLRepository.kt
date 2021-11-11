package it.github.samuele794.composedeepl.repository

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import it.github.samuele794.composedeepl.BuildConfig

interface DeepLRepository {
    suspend fun getSourceLanguages(): String
    suspend fun getTargetLanguages(): String
}

class DeepLRepositoryImpl : DeepLRepository {
    private val httpCLient = HttpClient(CIO) {
//        install(JsonFeature) {
//            serializer = KotlinxSerializer()
//        }
        install(UserAgent) {
            agent = BuildConfig.APPLICATION_ID
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        HttpRequestPipeline

        defaultRequest {
            host = BuildConfig.DEEPL_BASE_URL
//            contentType(ContentType.Application.FormUrlEncoded)
//            accept(ContentType.Application.Json)
            url {
                parameters.append("auth_key", BuildConfig.DEEPL_API_KEY)
            }
        }
    }.apply {

    }

    override suspend fun getTargetLanguages(): String {
        return httpCLient.submitForm(" /v2/languages") {
            contentType(ContentType.Application.FormUrlEncoded)
            accept(ContentType.Text.Any)
            body = Parameters.build {
                append("type", "target")
            }
        }
    }

    override suspend fun getSourceLanguages(): String {
        return httpCLient.submitForm("/v2/languages") {
            contentType(ContentType.Application.FormUrlEncoded)
            accept(ContentType.Text.Any)
            body = Parameters.build {
                append("type", "source")
            }
        }
    }
}