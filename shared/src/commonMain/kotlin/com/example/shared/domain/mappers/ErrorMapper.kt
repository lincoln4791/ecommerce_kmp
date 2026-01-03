package com.example.shared.domain.mappers

import com.example.shared.data.api.ForbiddenException
import com.example.shared.data.api.ServerException
import com.example.shared.data.api.UnauthorizedException
import com.example.shared.domain.model.AppError
import io.ktor.client.plugins.*
import io.ktor.http.*

object ErrorMapper {

    fun fromThrowable(t: Throwable): AppError {
        return when (t) {
            is HttpRequestTimeoutException -> AppError.Timeout
            is ClientRequestException -> fromStatusCode(t.response.status)
            is ServerResponseException -> AppError.Server
            is UnauthorizedException -> AppError.Unauthorized
            is ForbiddenException -> AppError.Forbidden
            is ServerException -> AppError.Server
            else -> AppError.Network
        }
    }

    fun fromStatusCode(status: HttpStatusCode): AppError {
        return when (status.value) {
            401 -> AppError.Unauthorized
            403 -> AppError.Forbidden
            404 -> AppError.NotFound
            in 500..599 -> AppError.Server
            else -> AppError.Unknown("HTTP ${status.value}")
        }
    }
}
