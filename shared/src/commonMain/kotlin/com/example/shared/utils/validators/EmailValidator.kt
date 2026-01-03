package com.example.shared.utils.validators

import com.example.shared.domain.model.ValidationResultBase

object EmailValidator {

    private val EMAIL_REGEX =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    fun validate(email: String?): ValidationResultBase {
        val value = email?.trim().orEmpty()

        return when {
            value.isEmpty() ->
                ValidationResultBase.Error("Email cannot be empty")

            !EMAIL_REGEX.matches(value) ->
                ValidationResultBase.Error("Invalid email address")

            else ->
                ValidationResultBase.Success
        }
    }
}