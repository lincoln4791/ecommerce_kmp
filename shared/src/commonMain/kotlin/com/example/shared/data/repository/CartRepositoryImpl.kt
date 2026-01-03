package com.example.shared.data.repository

import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.cart.CartBulkUpdateResponse
import com.example.shared.data.model.responses.cart.GetCartResponse
import com.example.shared.domain.repository.CartRepository

class CartRepositoryImpl(
    private val api: EcommerceApi
) : CartRepository {

    override suspend fun addToCart(addToCartRequest: AddToCartRequest): AddToCartResponse {
        return api.addToCart(addToCartRequest)
    }

    override suspend fun cartUpdateBulk(cartBulkUpdateRequest: CartBulkUpdateRequest): CartBulkUpdateResponse {
        return api.cartBulkUpdate(cartBulkUpdateRequest)
    }

    override suspend fun getCart(): GetCartResponse {
        return api.getCart()
    }

}