package com.example.shared.domain.model

sealed class AppError {
    object Network : AppError()
    object Timeout : AppError()
    object Unauthorized : AppError()
    object Forbidden : AppError()
    object NotFound : AppError()
    object Server : AppError()
    data class Validation(val error: String) : AppError()
    data class Unknown(val cause: String? = null) : AppError()
}

fun AppError.toUiMessage(): String = when (this) {
    AppError.Network -> "Network Error"
    AppError.Timeout -> "Request timeout"
    AppError.Unauthorized -> "Unauthorized"
    AppError.Forbidden -> "Access denied"
    AppError.NotFound -> "Not found"
    AppError.Server -> "Server error"
    is AppError.Validation -> error
    is AppError.Unknown -> "Something went wrong"
}
