package com.example.shared.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val name: String,
    val phone: String,
    val email: String,
    val password: String
)
