package com.example.ecommerce

import android.app.Application
import com.example.ecommerce.DI.androidModule
import com.example.ecommerce.DI.viewModelModule
import com.example.shared.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    init {
        startKoin {
            androidContext(this@MyApplication)
            modules(
                sharedModule,
                androidModule,
                viewModelModule
            )
        }
    }
}