package com.example.ecommerce.ui.theme.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerce.navigation.Screen
import com.example.ecommerce.ui.theme.cart.CartViewModel
import com.example.ecommerce.ui.theme.order.OrderViewModel
import com.example.ecommerce.utils.UiEvent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
    cartViewModel: CartViewModel = koinViewModel(),
    orderViewModel: OrderViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ShowToast -> {
                        Toast.makeText(
                            context,
                            event.message,
                            Toast.LENGTH_SHORT
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
                            context,
                            event.message,
                            Toast.LENGTH_SHORT
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("Home Page")
        Button(onClick = {
            viewModel.logout()
        }) {
            Text(text = "Logout")
        }

        Button(onClick = {
            //cartViewModel.addToCart()
            //cartViewModel.getCart()
            //cartViewModel.cartBulkUpdate()
            //orderViewModel.orderFromCart()
            orderViewModel.getMyOrders()
        }) {
            Text(text = "Add To Cart")
        }

        Button(onClick = {
            navController.navigate(Screen.Products.route)
        }) {
            Text("navigate")
        }
    }
}
