package com.example.ecommerce.DI

import com.example.ecommerce.ui.theme.cart.CartViewModel
import com.example.ecommerce.ui.theme.home.HomeViewModel
import com.example.ecommerce.ui.theme.login.LoginViewModel
import com.example.ecommerce.ui.theme.order.OrderViewModel
import com.example.ecommerce.ui.theme.products.ProductViewModel
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