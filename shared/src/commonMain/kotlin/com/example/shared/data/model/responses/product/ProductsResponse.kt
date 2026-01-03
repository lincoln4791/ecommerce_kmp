package com.example.shared.data.model.responses.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val `data`: ProductsData?,
    val errors: String?,
    val message: String?,
    val status_code: Int?,
)

@Serializable
data class ProductsData(
    val items: List<ProductsDataItem>,
    val last: Boolean,
    val page: Int,
    val size: Int,
    val total: Int,
    val totalPages: Int,
)

@Serializable
data class ProductsDataItem(
    val category: ProductCategory,
    val id: Int,
    val model: ProductModel,
    val name: String,
    val price: Double,
    val productStatus: String,
    val stock: Int,
)

@Serializable
data class ProductBrand(
    val description: String?,
    val id: Int,
    val name: String,
)

@Serializable
data class ProductCategory(
    val description: String?,
    val id: Int,
    val name: String,
)

@Serializable
data class ProductModel(
    val brand: ProductBrand,
    val description: String?,
    val id: Int,
    val name: String,
)