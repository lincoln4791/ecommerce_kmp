package com.example.shared.domain.usecase.cart

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.repository.CartRepository


class AddToCartUseCase(
    private val repo: CartRepository
) {
    suspend operator fun invoke(
        addToCartRequest: AddToCartRequest
    ): AddToCartResponseBase {
        return try {
            val response = repo.addToCart(addToCartRequest)

            when {
                response.data != null -> {
                    AddToCartResponseBase.Success(response)
                }
                response.errors != null -> {
                    AddToCartResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    AddToCartResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            AddToCartResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

