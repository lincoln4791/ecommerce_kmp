package com.example.shared.data.repository

import com.example.shared.data.api.AuthApi
import com.example.shared.data.model.requests.LoginRequest
import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.data.model.requests.RegisterRequest
import com.example.shared.data.model.responses.auth.LoginResponse
import com.example.shared.data.model.responses.auth.RefreshTokenResponse
import com.example.shared.data.model.responses.auth.RegistrationResponse
import com.example.shared.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {
    override suspend fun login(email: String, password: String): LoginResponse {
        return api.login(LoginRequest(email, password))
    }

    override suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse {
        return  api.refreshToken(refreshTokenRequest)
    }

    override suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String
    ): RegistrationResponse {
        return api.register(RegisterRequest(name, phone, email, password))
    }
}