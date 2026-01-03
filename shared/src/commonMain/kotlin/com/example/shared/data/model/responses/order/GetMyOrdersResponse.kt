package com.example.shared.data.model.responses.order

import kotlinx.serialization.Serializable

@Serializable
data class GetMyOrdersResponse(
    val `data`: List<OrderData>?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)