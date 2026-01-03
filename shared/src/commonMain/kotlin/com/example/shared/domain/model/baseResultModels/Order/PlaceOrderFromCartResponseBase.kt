package com.example.shared.domain.model.baseResultModels.Order

import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.order.PlaceOrderFromCartResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class PlaceOrderFromCartResponseBase {
    data class Success(val data: PlaceOrderFromCartResponse) : PlaceOrderFromCartResponseBase()
    data class Error(val error: AppError) : PlaceOrderFromCartResponseBase()
}