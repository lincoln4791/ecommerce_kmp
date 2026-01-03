package com.example.shared.data.model.responses.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val `data`: LoginData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)