package com.example.shared.data.model.responses.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartBulkUpdateResponse(
    val `data`: List<CartData>?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)