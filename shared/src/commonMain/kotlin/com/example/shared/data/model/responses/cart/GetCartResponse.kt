package com.example.shared.data.model.responses.cart

import com.example.shared.data.model.responses.product.ProductsData
import com.example.shared.data.model.responses.product.ProductsDataItem
import kotlinx.serialization.Serializable

@Serializable
data class GetCartResponse(
    val `data`: List<CartData>?,
    val errors: String?,
    val message: String?,
    val status_code: Int?
)