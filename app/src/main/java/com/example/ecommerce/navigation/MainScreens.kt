package com.example.ecommerce.navigation

import android.util.Log

sealed class MainScreens(val route: String, val title:String) {
    object Feed : MainScreens("feed","Feed")
    object Home : MainScreens("home","Home")

    object Delivery : MainScreens("delivery","Delivery")
    object Products : MainScreens("products","Products")
    object Cart : MainScreens("cart","Cart")
    object OrderHistory : MainScreens("order_history","Order History")

    object Profile : MainScreens("profile/{userId}","Profile") {
        fun createRoute(userId: String) = "profile/$userId"
    }

    companion object {
        private val allRoutes: List<MainScreens> = listOf(
            Feed,
            Delivery,
            //Home,
            Products,
            Cart,
            OrderHistory,
            Profile
        )

        fun fromRoute(route: String): MainScreens? {
            allRoutes.forEachIndexed { index, screen ->
                Log.d("TAG", "index=$index screen=${screen.route}")
            }
            return null
        }



         /*   allRoutes.find {
                 Log.d("tag","List is -> ${allRoutes}")
                 it.route==route
                         ||
                         route.startsWith(it.route.substringBefore("/{"))
             }*/
            /*all.firstOrNull { screen ->
                route == screen.route || route.startsWith(screen.route.substringBefore("/{"))
            }*/
    }

}

