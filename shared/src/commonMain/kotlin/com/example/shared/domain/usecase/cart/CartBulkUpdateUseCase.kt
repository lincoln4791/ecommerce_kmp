package com.example.shared.domain.usecase.cart

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.model.baseResultModels.cart.CartBulkUpdateResponseBase
import com.example.shared.domain.repository.CartRepository


class CartBulkUpdateUseCase(
    private val repo: CartRepository
) {
    suspend operator fun invoke(
        cartBulkUpdateRequest: CartBulkUpdateRequest
    ): CartBulkUpdateResponseBase {
        return try {
            val response = repo.cartUpdateBulk(cartBulkUpdateRequest)

            when {
                response.data != null -> {
                    CartBulkUpdateResponseBase.Success(response)
                }
                response.errors != null -> {
                    CartBulkUpdateResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    CartBulkUpdateResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            CartBulkUpdateResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

