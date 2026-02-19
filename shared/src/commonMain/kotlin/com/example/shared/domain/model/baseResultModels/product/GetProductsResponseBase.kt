package com.example.shared.domain.model.baseResultModels.product

import com.example.shared.data.model.responses.product.ProductsResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class GetProductsResponseBase {
    data class Success(val data: ProductsResponse) : GetProductsResponseBase()
    data class Error(val error: AppError) : GetProductsResponseBase()
}