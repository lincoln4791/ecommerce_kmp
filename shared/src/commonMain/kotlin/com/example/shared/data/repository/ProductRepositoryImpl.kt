package com.example.shared.data.repository

import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.model.responses.product.ProductsResponse
import com.example.shared.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: EcommerceApi
) : ProductRepository {

    override suspend fun getProducts(): ProductsResponse {
        return api.getProducts()
    }

}