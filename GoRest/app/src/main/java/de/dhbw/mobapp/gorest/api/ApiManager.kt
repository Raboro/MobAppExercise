package de.dhbw.mobapp.gorest.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpCallValidator
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.coroutines.cancellation.CancellationException

class ApiManager {
    private val apiKey = "02f11ad5823f8a9ab7fda45866e4c3c9fc0dec438ab2de30103a18b361c75297"

    var jsonHttpClient = HttpClient {
        expectSuccess = true

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        defaultRequest {
            url.host = "gorest.co.in"
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = "/public/v2/" + url.encodedPath
            contentType(ContentType.Application.Json)

            header(HttpHeaders.Authorization, "Bearer $apiKey")
        }

        HttpResponseValidator {
            getCustomResponseValidator(this)
        }
    }

    var imageHttpClient = HttpClient {
        HttpResponseValidator {
            getCustomResponseValidator(this)
        }
    }

    private fun getCustomResponseValidator(responseValidator: HttpCallValidator.Config): HttpCallValidator.Config {
        responseValidator.handleResponseExceptionWithRequest { exception, _ ->
            var exceptionResponseText = exception.message ?: "Unknown Error occurred. Please contact your administrator"

            if (exception is ClientRequestException) {
                // 4xx
                val exceptionResponse = exception.response
                exceptionResponseText = exceptionResponse.bodyAsText()
            } else if (exception is ServerResponseException) {
                // 5xx
                val exceptionResponse = exception.response
                exceptionResponseText = exceptionResponse.bodyAsText()
            }
            throw CancellationException(exceptionResponseText)
        }
        return responseValidator
    }
}
