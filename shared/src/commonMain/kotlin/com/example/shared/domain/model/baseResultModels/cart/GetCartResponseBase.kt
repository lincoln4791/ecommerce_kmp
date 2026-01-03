package com.example.shared.domain.model.baseResultModels.cart

import com.example.shared.data.model.responses.cart.GetCartResponse
import com.example.shared.data.model.responses.product.ProductsResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class GetCartResponseBase {
    data class Success(val data: GetCartResponse) : GetCartResponseBase()
    data class Error(val error: AppError) : GetCartResponseBase()
}