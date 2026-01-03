package com.example.shared.domain.model

sealed class ValidationResultBase {
    object Success : ValidationResultBase()
    data class Error(val message: String) : ValidationResultBase()
}