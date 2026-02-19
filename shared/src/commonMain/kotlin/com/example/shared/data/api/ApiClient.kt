package com.example.shared.data.api

import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.data.model.responses.auth.RefreshTokenResponse
import com.example.shared.log
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.auth.providers.RefreshTokensParams


class ApiClient(
    private val userSession: UserSession
) {

    private val client = HttpClient {

        install(DefaultRequest) {
            header("Accept", "application/json")
        }

        install(Auth) {
            bearer {

                loadTokens {
                    log("init token") // 👈 ADD THIS
                    userSession.getAccessToken()?.let { token ->
                        log("DEBUG TOKEN FROM SESSION = $token") // 👈 ADD THIS
                        BearerTokens(
                            accessToken = token,
                            refreshToken = ""
                        )
                    }?:run {
                        log("No Bearer Token - log") // 👈 ADD THIS
                        null
                    }
                }

                refreshTokens {

                    val refreshToken = userSession.getUser()?.refreshToken
                        ?: return@refreshTokens null

                    try {
                        val response: RefreshTokenResponse =
                            client.post("${EcommerceApi.baseUrl}/auth/refresh") {
                                contentType(ContentType.Application.Json)
                                setBody(
                                    RefreshTokenRequest(refreshToken)
                                    //mapOf("refresh_token" to refreshToken)
                                )
                            }.body()

                        val user = userSession.getUser()
                        user?.token=response.data!!.access_token
                        userSession.saveUser(user!!)

                        BearerTokens(
                            accessToken = response.data!!.access_token,
                            refreshToken = refreshToken
                        )

                    } catch (e: Exception) {
                        print(e)
                        userSession.logout()
                        null
                    }
                }



                // 🔥 THIS IS REQUIRED
                sendWithoutRequest {
                    log("AUTH APPLIED TO: ${it.url}") // 👈 ADD THIS
                    Napier.d("AUTH APPLIED TO: ${it.url}") // 👈 ADD THIS
                    true
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }

        HttpResponseValidator {
            validateResponse { response ->
                when (response.status.value) {
                    401 -> throw UnauthorizedException()
                    403 -> throw ForbiddenException()
                    in 500..599 -> throw ServerException()
                }
            }
        }
    }

    internal suspend inline fun <reified T> get(
        url: String,
        params: Map<String, String>? = null
    ): T =
        client.get(url) {
            contentType(ContentType.Application.Json)
            params?.forEach { (k, v) -> parameter(k, v) }
        }.body()

    internal suspend inline fun <reified T> post(
        url: String,
        body: Any?
    ): T =
        client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
}



class UnauthorizedException : Exception()
class ForbiddenException : Exception()
class ServerException : Exception()
