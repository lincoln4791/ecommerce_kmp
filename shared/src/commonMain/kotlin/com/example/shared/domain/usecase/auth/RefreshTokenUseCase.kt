package com.example.shared.domain.usecase.auth

import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.model.baseResultModels.auth.RefreshTokenResponseBase
import com.example.shared.domain.repository.AuthRepository

class RefreshTokenUseCase(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(
        refreshTokenRequest: RefreshTokenRequest
    ): RefreshTokenResponseBase {
        return try {
            val response = repo.refreshToken(refreshTokenRequest)
            when {
                response.data != null -> {
                    RefreshTokenResponseBase.Success(response)
                }
                response.errors != null -> {
                    RefreshTokenResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    RefreshTokenResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            RefreshTokenResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

