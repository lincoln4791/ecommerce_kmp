package com.example.ecommerce.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AuthScreens(val route: String) {
    object Login : AuthScreens("login")
}