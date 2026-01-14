package com.example.shared.di

import com.example.shared.data.api.ApiClient
import com.example.shared.data.api.AuthApi
import com.example.shared.data.api.EcommerceApi
import com.example.shared.data.keyValueStorage.JsonStorage
import com.example.shared.data.keyValueStorage.UserSession
import org.koin.dsl.module

val sharedModule = module {

    single { JsonStorage(get()) }
    single { UserSession(get()) }
    single { ApiClient(get()) }

    single { AuthApi(get()) }
    single { EcommerceApi(get()) }

}
