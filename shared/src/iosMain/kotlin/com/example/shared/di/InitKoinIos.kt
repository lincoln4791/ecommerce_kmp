package com.example.shared.di

import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(sharedModule,sharedControllerModule,sharedRepositoryModule,iosModule)
    }
}
