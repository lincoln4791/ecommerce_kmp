package com.example.shared.presentation

import com.example.shared.data.api.ApiClient
import com.example.shared.data.api.AuthApi
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.model.baseResultModels.auth.RefreshTokenResponseBase
import com.example.shared.domain.usecase.auth.LoginUseCase
import com.example.shared.domain.usecase.auth.RefreshTokenUseCase
import com.example.shared.domain.usecase.auth.RegisterUseCase

class AuthController(
    private val userSession: UserSession
) {

    private val apiClient = ApiClient(userSession)
    private val api = AuthApi(apiClient)
    private val repo = AuthRepositoryImpl(api)
    private val loginUseCase = LoginUseCase(repo)

    private val registerUseCase = RegisterUseCase(repo)
    private val refreshTokenUseCase = RefreshTokenUseCase(repo)


    suspend fun login(
        email: String,
        password: String
    ): LoginResponseBase {
        return loginUseCase(email, password)
    }

    suspend fun refreshToken(
        refreshTokenRequest: RefreshTokenRequest
    ): RefreshTokenResponseBase {
        return refreshTokenUseCase(refreshTokenRequest)
    }

}