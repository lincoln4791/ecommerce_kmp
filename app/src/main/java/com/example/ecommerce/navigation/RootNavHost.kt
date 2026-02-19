package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.shared.data.keyValueStorage.UserSession

@Composable
fun RootNavHost(navHostController: NavHostController, userSession: UserSession) {
    val isLoggedIn by userSession.isLoggedInState.collectAsStateWithLifecycle()
    if (isLoggedIn) {
        MainScreen(navHostController)
    } else {
        AuthScreen(
           navHostController
        )
    }
}
