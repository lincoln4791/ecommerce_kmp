package com.example.shared.domain.usecase.order

import com.example.shared.domain.mappers.ErrorMapper
import com.example.shared.domain.model.AppError
import com.example.shared.domain.model.baseResultModels.Order.GetMyOrdersResponseBase
import com.example.shared.domain.model.baseResultModels.Order.PlaceOrderFromCartResponseBase
import com.example.shared.domain.repository.OrderRepository


class GetMyOrdersUseCase(
    private val repo: OrderRepository
) {
    suspend operator fun invoke(
    ): GetMyOrdersResponseBase {
        return try {
            val response = repo.getMyOrders()

            when {
                response.data != null -> {
                    GetMyOrdersResponseBase.Success(response)
                }
                response.errors != null -> {
                    GetMyOrdersResponseBase.Error(
                        AppError.Validation(response.errors)
                    )
                }
                else -> {
                    GetMyOrdersResponseBase.Error(AppError.Unknown())
                }
            }

        } catch (t: Throwable) {
            GetMyOrdersResponseBase.Error(ErrorMapper.fromThrowable(t))
        }
    }
}

