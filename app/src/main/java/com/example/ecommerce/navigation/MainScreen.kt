@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.ecommerce.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController : NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val bottomDestinations = listOf(
        MainScreens.Home,
        MainScreens.Feed,
        MainScreens.Delivery
    )
    val topLevelRoutes = bottomDestinations.map { it.route }

    // Observe current destination
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainScreens.Home.route

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menu",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                NavigationDrawerItem(
                    label = { Text(MainScreens.Home.route.capitalize()) },
                    selected = false,
                    onClick = { scope.launch {
                        drawerState.close()
                        navController.navigate(MainScreens.Home.route)
                    } }
                )

//                NavigationDrawerItem(
//                    label = { Text(Screens.Details.route.capitalize(Locale.ROOT)) },
//                    selected = false,
//                    onClick = { scope.launch {
//                        drawerState.close()
//                        navController.navigate(Screens.Details.route)
//                    } }
//                )
//
//                NavigationDrawerItem(
//                    label = { Text(Screens.Login.route.capitalize()) },
//                    selected = false,
//                    onClick = { scope.launch {
//                        drawerState.close()
//                        navController.navigate(Screens.Login.route)
//                    } }
//                )


            }
        }
    ) {
        Scaffold(
            topBar = {
                //if (currentRoute == MainScreens.Home.route || currentRoute == MainScreens.Feed.route || currentRoute == MainScreens.Delivery.route){
                    TopAppBar(
                        title = {
                            val screen = MainScreens.fromRoute(currentRoute)
                            Text(currentRoute.capitalize(Locale.current))
                                },
                        navigationIcon = {
                            if (currentRoute in topLevelRoutes) {
                                // ☰ Menu
                                IconButton(onClick = {
                                    scope.launch { drawerState.open() }
                                }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            } else {
                                // ← Back
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        }
                    )

                //}

            },
            bottomBar = {
                if (currentRoute in bottomDestinations.map { it.route }) {
                    NavigationBar {
                        val currentDestination = navController.currentDestination?.route
                        bottomDestinations.forEach { screen ->
                            NavigationBarItem(
                                selected = currentDestination == screen.route,
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    /*Icon(
                                        imageVector = screen.icon,
                                        contentDescription = screen.title
                                    )*/
                                },
                                label = { Text(screen.title) }
                            )
                        }
                    }
                }

            },

            content = { innerPadding ->
                MainNavHost(navController, modifier = Modifier.padding(innerPadding))
            }
        )
    }
}
