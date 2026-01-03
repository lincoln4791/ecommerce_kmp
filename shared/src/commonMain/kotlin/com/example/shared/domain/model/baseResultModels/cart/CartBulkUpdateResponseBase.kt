package com.example.shared.domain.model.baseResultModels.cart

import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.cart.CartBulkUpdateResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class CartBulkUpdateResponseBase {
    data class Success(val data: CartBulkUpdateResponse) : CartBulkUpdateResponseBase()
    data class Error(val error: AppError) : CartBulkUpdateResponseBase()
}