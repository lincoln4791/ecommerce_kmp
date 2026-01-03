package com.example.shared.data.model.responses.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val `data`: LoginData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)


@Serializable
data class LoginData(
    val email: String,
    val name: String,
    val phone: String,
    val refreshToken: String,
    val role: String,
    var token: String
)