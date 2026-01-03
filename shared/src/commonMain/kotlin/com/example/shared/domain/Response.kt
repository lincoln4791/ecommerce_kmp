package com.example.shared.domain

sealed class Response<out T> {

    data class Success<T>(val data: T) : Response<T>()

    data class Error(
        val message: String,
        val cause: Throwable? = null
    ) : Response<Nothing>()
}