package com.example.ecommerce.DI

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        _root_ide_package_.com.example.ecommerce.ui.views.login.LoginViewModel(
            authController = get(),
            userSession = get()
        )
    }

    //Home
    viewModel {
        _root_ide_package_.com.example.ecommerce.ui.views.home.HomeViewModel(
            authController = get(),
            userSession = get()
        )
    }

    //products
    viewModel {
        _root_ide_package_.com.example.ecommerce.ui.views.products.ProductViewModel(
            productController = get(),
        )
    }

    viewModel {
        _root_ide_package_.com.example.ecommerce.ui.views.cart.CartViewModel(
            cartController = get(),
        )
    }

    viewModel {
        _root_ide_package_.com.example.ecommerce.ui.views.order.OrderViewModel(
            orderController = get(),
        )
    }
}