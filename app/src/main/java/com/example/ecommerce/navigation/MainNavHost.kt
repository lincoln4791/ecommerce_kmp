package com.example.ecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommerce.ui.views.cart.CartScreen
import com.example.ecommerce.ui.views.delivery.DeliveryScreen
import com.example.ecommerce.ui.views.feed.FeedScreen
import com.example.ecommerce.ui.views.home.HomeScreen
import com.example.ecommerce.ui.views.order.OrdersScreen
import com.example.ecommerce.ui.views.products.ProductsScreen

@Composable
fun MainNavHost(navHostController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navHostController, startDestination = MainScreens.Home.route,modifier=modifier) {
        composable(MainScreens.Home.route) {
            HomeScreen(navHostController)
        }
        composable(MainScreens.Products.route) {
            ProductsScreen(navHostController)
        }
        composable(MainScreens.Cart.route) {
            CartScreen(navHostController)
        }
        composable(MainScreens.OrderHistory.route) {
            OrdersScreen(navHostController)
        }
        composable(MainScreens.Feed.route) {
            FeedScreen(navHostController)
        }
        composable(MainScreens.Delivery.route) {
            DeliveryScreen(navHostController)
        }
    }
}
