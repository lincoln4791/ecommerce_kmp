package com.example.ecommerce.ui.views.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerce.R
import com.example.ecommerce.navigation.MainScreens
import com.example.ecommerce.ui.views.cart.CartViewModel
import com.example.ecommerce.ui.views.home.model.HomeMenuItem
import com.example.ecommerce.ui.views.home.widgets.HomeMenuCard
import com.example.ecommerce.ui.views.home.widgets.SlidingBanner
import com.example.ecommerce.ui.views.order.OrderViewModel
import com.example.ecommerce.utils.UiEvent
import com.example.shared.DemoState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
    cartViewModel: CartViewModel = koinViewModel(),
    orderViewModel: OrderViewModel = koinViewModel(),
) {
    val bannerImages = listOf(
        R.drawable.one, R.drawable.two, R.drawable.three
    )
    val context = LocalContext.current

    val demoState = remember { DemoState() }

    LaunchedEffect(Unit) {
        launch {
            demoState.startTimer()
        }

        launch {
            demoState.count.collect {
                Log.d("tag", "ggwp: $it")
            }
        }
    }

    LaunchedEffect(Unit) {
        launch {

            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ShowToast -> {
                        Toast.makeText(
                            context, event.message, Toast.LENGTH_SHORT
                        ).show()
                    }

                    is UiEvent.NavigateTo -> {
                        navController.navigate(event.screen.route) /*{
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }*/
                    }

                    else -> {}
                }
            }
        }

        launch {
            cartViewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ShowToast -> {
                        Toast.makeText(
                            context, event.message, Toast.LENGTH_SHORT
                        ).show()
                    }

                    is UiEvent.NavigateTo -> {
                        navController.navigate(event.screen.route) /*{
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }*/
                    }

                    else -> {}
                }
            }
        }


    }


    val menuItems = listOf(
        HomeMenuItem("Market", Icons.Default.Star, MainScreens.Products.route),
        HomeMenuItem("My Cart", Icons.Default.ShoppingCart, MainScreens.Cart.route),
        HomeMenuItem("My Orders", Icons.Default.Favorite, MainScreens.OrderHistory.route),
        HomeMenuItem("Profile", Icons.Default.Person, MainScreens.Products.route),
        HomeMenuItem("Help", Icons.Default.Settings, MainScreens.Products.route),
        HomeMenuItem("Logout", Icons.Default.ExitToApp, MainScreens.Products.route)
    )

    HomeScreenUi(menuItems,bannerImages, onClick = {it->
        navController.navigate(it.path)
    }, onLogoutClick = {
        viewModel.logout()
    })

}

@Composable
fun HomeScreenUi(
    menuItems: List<HomeMenuItem>,
    bannerImages: List<Int>,
    onClick: (menuItem: HomeMenuItem) -> Unit,
    onLogoutClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Title
        Text(
            text = "Home Screen 🎉",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(menuItems) { item ->
                HomeMenuCard(item) {
                    onClick(item)
                    //navController.navigate(item.path)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Bottom banner image
        SlidingBanner(images = bannerImages)
        Button(onClick = {
            onLogoutClick()
            //viewModel.logout()
        }) {
            Text(text = "Logout")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    val menuItems = listOf(
        HomeMenuItem("Market", Icons.Default.Star, MainScreens.Products.route),
        HomeMenuItem("My Cart", Icons.Default.ShoppingCart, MainScreens.Cart.route),
        HomeMenuItem("My Orders", Icons.Default.Favorite, MainScreens.OrderHistory.route),
        HomeMenuItem("Profile", Icons.Default.Person, MainScreens.Products.route),
        HomeMenuItem("Help", Icons.Default.Settings, MainScreens.Products.route),
        HomeMenuItem("Logout", Icons.Default.ExitToApp, MainScreens.Products.route)
    )
    val bannerImages = listOf(
        R.drawable.one, R.drawable.two, R.drawable.three
    )
    HomeScreenUi(
        menuItems,bannerImages,{},{}
    )
}
