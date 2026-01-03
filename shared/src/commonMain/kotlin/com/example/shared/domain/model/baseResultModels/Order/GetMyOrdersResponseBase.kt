package com.example.shared.domain.model.baseResultModels.Order

import com.example.shared.data.model.responses.order.GetMyOrdersResponse
import com.example.shared.domain.model.AppError
import kotlinx.serialization.Serializable

@Serializable
sealed class GetMyOrdersResponseBase {
    data class Success(val data: GetMyOrdersResponse) : GetMyOrdersResponseBase()
    data class Error(val error: AppError) : GetMyOrdersResponseBase()
}