package com.example.shared.di

import com.example.shared.data.keyValueStorage.IosKeyValueStorage
import com.example.shared.data.keyValueStorage.KeyValueStorage
import org.koin.dsl.module

val iosModule = module {
    single<KeyValueStorage> {
        IosKeyValueStorage()
    }
}
