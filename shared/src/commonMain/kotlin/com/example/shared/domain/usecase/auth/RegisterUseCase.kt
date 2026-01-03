package com.example.shared.domain.usecase.auth

import com.example.shared.data.model.responses.auth.RegistrationResponse
import com.example.shared.domain.repository.AuthRepository

class RegisterUseCase(
    private val repo: AuthRepository
) {
    suspend operator fun invoke(
        name: String,
        phone: String,
        email: String,
        password: String
    ): RegistrationResponse {
        require(name.isNotBlank())
        require(phone.isNotBlank())
        require(email.isNotBlank())
        require(password.length >= 6)
        return repo.register(name, phone ,email, password)
    }
}
