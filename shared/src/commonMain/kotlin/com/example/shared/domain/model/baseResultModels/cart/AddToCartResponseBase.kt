package com.example.shared.domain.model.baseResultModels.cart

import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class AddToCartResponseBase {
    data class Success(val data: AddToCartResponse) : AddToCartResponseBase()
    data class Error(val error: AppError) : AddToCartResponseBase()
}