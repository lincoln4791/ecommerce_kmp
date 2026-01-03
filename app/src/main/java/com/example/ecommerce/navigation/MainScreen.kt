package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.home.HomeScreen
import com.example.ecommerce.ui.theme.login.LoginScreen
import com.example.ecommerce.ui.theme.products.ProductsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Products.route) {
            ProductsScreen(navController)
        }
    }
}
