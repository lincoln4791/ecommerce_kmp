package com.example.shared.domain.repository

import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.data.model.responses.auth.LoginResponse
import com.example.shared.data.model.responses.auth.RefreshTokenResponse
import com.example.shared.data.model.responses.auth.RegistrationResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse
    suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
    ): RegistrationResponse
}