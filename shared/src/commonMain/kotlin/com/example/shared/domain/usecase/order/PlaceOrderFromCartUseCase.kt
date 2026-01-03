package com.example.shared.domain.usecase.order

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.responses.order.PlaceOrderFromCartResponse
import com.example.shared.domain.model.AppError
import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.baseResultModels.Order.PlaceOrderFromCartResponseBase
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.repository.CartRepository
import com.example.shared.domain.repository.OrderRepository


class PlaceOrderFromCartUseCase(
    private val repo: OrderRepository
) {
    suspend operator fun invoke(
    ): PlaceOrderFromCartResponseBase {
        return try {
            val response = repo.placeOrderFromCart()

            when {
                response.data != null -> {
                    PlaceOrderFromCartResponseBase.Success(response)
                }
                response.errors != null -> {
                    PlaceOrderFromCartResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    PlaceOrderFromCartResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            PlaceOrderFromCartResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

