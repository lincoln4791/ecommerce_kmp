package com.example.shared.di

import com.example.shared.presentation.AuthController
import com.example.shared.presentation.CartController
import com.example.shared.presentation.OrderController
import com.example.shared.presentation.ProductController
import org.koin.core.component.KoinComponent

object ControllerProvider : KoinComponent {

    fun getAuthController(): AuthController { return getKoin().get()}
    fun getProductsController(): ProductController { return getKoin().get()}
    fun getOrderController(): OrderController { return getKoin().get()}
    fun getCartController(): CartController { return getKoin().get()}
}