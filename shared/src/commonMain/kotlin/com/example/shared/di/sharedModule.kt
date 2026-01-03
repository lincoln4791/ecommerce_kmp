package com.example.shared.di

import com.example.shared.data.api.ApiClient
import com.example.shared.data.keyValueStorage.JsonStorage
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.presentation.AuthController
import com.example.shared.presentation.CartController
import com.example.shared.presentation.OrderController
import com.example.shared.presentation.ProductController
import org.koin.dsl.module

val sharedModule = module {

    single { JsonStorage(get()) }
    single { UserSession(get()) }
    single { ApiClient(get()) }



    single { AuthController(get()) }
    single { ProductController(get()) }
    single { CartController(get()) }
    single { OrderController(get()) }
}
