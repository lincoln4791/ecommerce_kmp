package com.example.shared.domain.repository

import com.example.shared.data.model.responses.product.ProductsResponse

interface ProductRepository {
    suspend fun getProducts(): ProductsResponse
}