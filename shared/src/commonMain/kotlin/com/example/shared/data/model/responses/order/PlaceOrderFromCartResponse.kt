package com.example.shared.data.model.responses.order

import com.example.shared.data.model.responses.product.ProductsDataItem
import kotlinx.serialization.Serializable

@Serializable
data class PlaceOrderFromCartResponse(
    val `data`: OrderData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)
@Serializable
data class OrderData(
    val deliveryStatus: String,
    val id: Int,
    val items: List<OrderDataItem>,
    val paymentMethod: String,
    val totalAmount: Double,
    val userId: Int
)
@Serializable
data class OrderDataItem(
    val id: Int,
    val price: Double,
    val product: ProductsDataItem,
    val quantity: Int
)