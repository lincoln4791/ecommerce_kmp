package com.example.shared.di

import com.example.shared.data.keyValueStorage.UserSession
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UserSessionProvider : KoinComponent {
    val userSession: UserSession by inject()
}