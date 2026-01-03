package com.example.shared.domain.model.baseResultModels.auth

import com.example.shared.data.model.responses.auth.LoginResponse
import com.example.shared.data.model.responses.auth.RefreshTokenResponse
import com.example.shared.domain.model.AppError

sealed class RefreshTokenResponseBase {
    data class Success(val data: RefreshTokenResponse) : RefreshTokenResponseBase()
    data class Error(val error: AppError) : RefreshTokenResponseBase()
}