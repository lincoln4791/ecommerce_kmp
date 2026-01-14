package com.example.shared.presentation
import com.example.shared.domain.model.baseResultModels.product.GetProductsResponseBase
import com.example.shared.domain.repository.ProductRepository
import com.example.shared.domain.usecase.product.GetProductsUseCase

class ProductController(
    private val repo: ProductRepository,
) {

    private val getProductsUseCase = GetProductsUseCase(repo)

    suspend fun getProducts(
    ): GetProductsResponseBase {
        return getProductsUseCase()
    }
}