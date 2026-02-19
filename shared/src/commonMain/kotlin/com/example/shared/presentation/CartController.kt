package com.example.shared.presentation

import com.example.shared.data.api.ApiClient
import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.data.repository.CartRepositoryImpl
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.cart.CartBulkUpdateResponseBase
import com.example.shared.domain.model.baseResultModels.cart.GetCartResponseBase
import com.example.shared.domain.repository.CartRepository
import com.example.shared.domain.usecase.cart.AddToCartUseCase
import com.example.shared.domain.usecase.cart.CartBulkUpdateUseCase
import com.example.shared.domain.usecase.cart.GetCartUseCase

class CartController(
    repo: CartRepository
) {
    private val addToCartUseCase = AddToCartUseCase(repo)
    private val getCartUseCase = GetCartUseCase(repo)
    private val cartBulkUpdateUseCase = CartBulkUpdateUseCase(repo)

    suspend fun addToCart(
        addToCartRequest: AddToCartRequest
    ): AddToCartResponseBase {
        return addToCartUseCase(addToCartRequest)
    }

    suspend fun cartBulkUpdate(
   cartBulkUpdateRequest: CartBulkUpdateRequest
    ): CartBulkUpdateResponseBase {
        return cartBulkUpdateUseCase(cartBulkUpdateRequest)
    }

    suspend fun getCart(
    ): GetCartResponseBase {
        return getCartUseCase()
    }
}