package com.example.shared.domain.usecase.product

import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.product.GetProductsResponseBase
import com.example.shared.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repo: ProductRepository,
) {

    suspend operator fun invoke(
    ): GetProductsResponseBase {
        return try {
            val response = repo.getProducts()

            when {
                response.data != null -> {
                    GetProductsResponseBase.Success(response)
                }

                response.errors != null -> {
                    GetProductsResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }

                else -> {
                    GetProductsResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            GetProductsResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

