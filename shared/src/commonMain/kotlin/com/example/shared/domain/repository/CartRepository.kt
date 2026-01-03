package com.example.shared.domain.repository

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.cart.CartBulkUpdateResponse
import com.example.shared.data.model.responses.cart.GetCartResponse
import com.example.shared.data.model.responses.product.ProductsResponse

interface CartRepository {
    suspend fun addToCart(addToCartRequest: AddToCartRequest): AddToCartResponse
    suspend fun cartUpdateBulk(cartBulkUpdateRequest: CartBulkUpdateRequest): CartBulkUpdateResponse
    suspend fun getCart(): GetCartResponse
}