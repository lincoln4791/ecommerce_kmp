package com.example.ecommerce.DI

import com.example.shared.data.keyValueStorage.AndroidKeyValueStorage
import com.example.shared.data.keyValueStorage.KeyValueStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<KeyValueStorage> {
        AndroidKeyValueStorage(androidContext())
    }
}
