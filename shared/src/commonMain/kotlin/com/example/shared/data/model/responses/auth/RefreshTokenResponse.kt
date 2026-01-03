package com.example.shared.data.model.responses.auth

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val `data`: RefreshTokenData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)
@Serializable
data class RefreshTokenData(
    val access_token: String
)