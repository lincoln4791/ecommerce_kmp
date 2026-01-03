package com.example.shared.domain.model.baseResultModels.auth

import com.example.shared.data.model.responses.auth.LoginResponse
import com.example.shared.domain.model.AppError

sealed class LoginResponseBase {
    data class Success(val data: LoginResponse) : LoginResponseBase()
    data class Error(val error: AppError) : LoginResponseBase()
}