package com.example.ecommerce.DI

import com.example.ecommerce.ui.views.cart.CartViewModel
import com.example.ecommerce.ui.views.home.HomeViewModel
import com.example.ecommerce.ui.views.login.LoginViewModel
import com.example.ecommerce.ui.views.order.OrderViewModel
import com.example.ecommerce.ui.views.products.ProductViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            authController = get(),
            userSession = get()
        )
    }

    //Home
    viewModel {
        HomeViewModel(
            authController = get(),
            userSession = get()
        )
    }

    //products
    viewModel {
        ProductViewModel(
            productController = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartController = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderController = get(),
        )
    }
}