package com.example.shared.data.repository

import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.model.responses.order.GetMyOrdersResponse
import com.example.shared.data.model.responses.order.PlaceOrderFromCartResponse
import com.example.shared.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val api: EcommerceApi
) : OrderRepository {
    override suspend fun placeOrderFromCart(): PlaceOrderFromCartResponse {
        return api.placeOrderFromCart()
    }

    override suspend fun getMyOrders(): GetMyOrdersResponse {
        return api.getMyOrders()
    }

}