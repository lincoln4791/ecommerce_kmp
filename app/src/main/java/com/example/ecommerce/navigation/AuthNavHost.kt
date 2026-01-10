package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerce.ui.views.login.LoginScreen

@Composable
fun AuthNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navHostController, startDestination = AuthScreens.Login.route,modifier=modifier) {
        composable(AuthScreens.Login.route) {
            LoginScreen(navHostController)
        }
    }
}
