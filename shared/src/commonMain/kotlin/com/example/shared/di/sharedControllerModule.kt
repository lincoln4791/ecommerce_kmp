package com.example.shared.di
import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import com.example.shared.presentation.AuthController
import com.example.shared.presentation.CartController
import com.example.shared.presentation.OrderController
import com.example.shared.presentation.ProductController
import org.koin.dsl.module

val sharedControllerModule = module {
    single { AuthController(get()) }
    single { ProductController(get()) }
    single { CartController(get()) }
    single { OrderController(get()) }
}
