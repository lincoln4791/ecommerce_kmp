package com.example.shared.presentation

import com.example.shared.data.api.ApiClient
import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.repository.OrderRepositoryImpl
import com.example.shared.domain.model.baseResultModels.Order.GetMyOrdersResponseBase
import com.example.shared.domain.model.baseResultModels.Order.PlaceOrderFromCartResponseBase
import com.example.shared.domain.usecase.order.GetMyOrdersUseCase
import com.example.shared.domain.usecase.order.PlaceOrderFromCartUseCase

class OrderController(
    private val userSession: UserSession
) {


    private val apiClient = ApiClient(userSession)
    private val api = EcommerceApi(apiClient)
    private val repo = OrderRepositoryImpl(api)
    private val placeOrderFromCartUseCase = PlaceOrderFromCartUseCase(repo)
    private val getMyOrdersUseCase = GetMyOrdersUseCase(repo)

    suspend fun placeOrderFromCart(
    ): PlaceOrderFromCartResponseBase {
        return placeOrderFromCartUseCase()
    }

    suspend fun getMyOrders(
    ): GetMyOrdersResponseBase {
        return getMyOrdersUseCase()
    }

}