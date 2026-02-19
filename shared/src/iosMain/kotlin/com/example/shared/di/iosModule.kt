package com.example.shared.di

import com.example.shared.data.keyValueStorage.IosKeyValueStorage
import com.example.shared.data.keyValueStorage.KeyValueStorage
import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import org.koin.dsl.module

val iosModule = module {
    single<KeyValueStorage> {
        IosKeyValueStorage()
    }
}
