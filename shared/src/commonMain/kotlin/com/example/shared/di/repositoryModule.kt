package com.example.shared.di
import com.example.shared.data.repository.AuthRepositoryImpl
import com.example.shared.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> {
        AuthRepositoryImpl(
            api = get()
        )
    }
}
