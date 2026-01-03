package com.example.shared.data.model.responses.cart

import com.example.shared.data.model.responses.product.ProductsDataItem
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartResponse(
    val `data`: CartData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)
@Serializable
data class CartData(
    val id: Int,
    val product: ProductsDataItem,
    val quantity: Int,
    val userId: Int
)