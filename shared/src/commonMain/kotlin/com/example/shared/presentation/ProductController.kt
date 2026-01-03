package com.example.shared.presentation

import com.example.shared.data.api.ApiClient
import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.repository.ProductRepositoryImpl
import com.example.shared.domain.model.baseResultModels.product.GetProductsResponseBase
import com.example.shared.domain.usecase.product.GetProductsUseCase

class ProductController(
    private val userSession: UserSession
) {


    private val apiClient = ApiClient(userSession)
    private val api = EcommerceApi(apiClient)
    private val repo = ProductRepositoryImpl(api)
    private val getProductsUseCase = GetProductsUseCase(repo)

    suspend fun getProducts(
    ): GetProductsResponseBase {
        return getProductsUseCase()
    }
}