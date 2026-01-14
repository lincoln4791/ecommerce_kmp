package com.example.shared.di
import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.data.repository.CartRepositoryImpl
import com.example.shared.data.repository.OrderRepositoryImpl
import com.example.shared.data.repository.ProductRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import com.example.shared.domain.repository.CartRepository
import com.example.shared.domain.repository.OrderRepository
import com.example.shared.domain.repository.ProductRepository
import org.koin.dsl.module

val sharedRepositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(api = get()) }
    single<ProductRepository> { ProductRepositoryImpl(api = get()) }
    single<OrderRepository> { OrderRepositoryImpl(api = get()) }
    single<CartRepository> { CartRepositoryImpl(api = get()) }
}
