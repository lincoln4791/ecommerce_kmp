package com.example.shared.data.model.requests.cart

import kotlinx.serialization.Serializable

@Serializable
data class CartBulkUpdateRequest(
    val items: List<CartBulkUpdateItem>
)

@Serializable
data class CartBulkUpdateItem(
    val productId: Int,
    val quantity: Int
)
