package com.example.shared.data.api

import com.example.shared.data.api.EcommerceApi.Companion.baseUrl
import com.example.shared.data.model.requests.LoginRequest
import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.data.model.requests.RegisterRequest
import com.example.shared.data.model.responses.auth.LoginResponse
import com.example.shared.data.model.responses.auth.RefreshTokenResponse
import com.example.shared.data.model.responses.auth.RegistrationResponse

class AuthApi(private val apiClient: ApiClient) {

    suspend fun login(request: LoginRequest): LoginResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.LOGIN,
            body = request
        )
    }

    suspend fun refreshToken(request: RefreshTokenRequest): RefreshTokenResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.REFRESH,
            body = request
        )
    }

    suspend fun register(request: RegisterRequest): RegistrationResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.REGISTRATION,
            body = request
        )
    }

}
