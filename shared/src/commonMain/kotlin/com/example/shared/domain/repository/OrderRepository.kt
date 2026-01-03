package com.example.shared.domain.repository

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.cart.CartBulkUpdateResponse
import com.example.shared.data.model.responses.cart.GetCartResponse
import com.example.shared.data.model.responses.order.GetMyOrdersResponse
import com.example.shared.data.model.responses.order.PlaceOrderFromCartResponse
import com.example.shared.data.model.responses.product.ProductsResponse

interface OrderRepository {
    suspend fun placeOrderFromCart(): PlaceOrderFromCartResponse
    suspend fun getMyOrders(): GetMyOrdersResponse
}