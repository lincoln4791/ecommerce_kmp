package com.example.shared.domain.usecase.auth

import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.repository.AuthRepository

/*class LoginUseCase(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResultBase {
        return try {
            val response = repo.login(email, password)

            if (response.data != null) {
                LoginResultBase.Success(response)
            } else {
                LoginResultBase.Error(response.errors ?: "Login failed")
            }

        } catch (e: Throwable) {
            LoginResultBase.Error(e.message ?: "Login failed")
        }
    }
}*/

class LoginUseCase(
    private val repo: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResponseBase {
        return try {
            val response = repo.login(email, password)

            when {
                response.data != null -> {
                    LoginResponseBase.Success(response)
                }
                response.errors != null -> {
                    LoginResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    LoginResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            LoginResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

