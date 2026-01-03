package com.example.shared.domain.usecase.cart

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.model.baseResultModels.cart.GetCartResponseBase
import com.example.shared.domain.repository.CartRepository


class GetCartUseCase(
    private val repo: CartRepository
) {
    suspend operator fun invoke(
    ): GetCartResponseBase {
        return try {
            val response = repo.getCart()

            when {
                response.data != null -> {
                    GetCartResponseBase.Success(response)
                }
                response.errors != null -> {
                    GetCartResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    GetCartResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            GetCartResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

