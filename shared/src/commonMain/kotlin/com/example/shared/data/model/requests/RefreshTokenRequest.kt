package com.example.shared.data.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val refresh_token: String
)