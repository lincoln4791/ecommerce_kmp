package com.example.ecommerce.ui.views.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.ecommerce.ui.views.cart.components.CartItemRowWidget
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(
    navController: NavHostController,
    viewModel: CartViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val totalPrice = uiState.cart.sumOf {
        it.product.price * it.quantity
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Cart",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(uiState.cart) { item ->
                CartItemRowWidget (
                    item = item,
                    onIncrease = {
                        /*uiState.cart = cartItems.map {
                            if (it.id == item.id)
                                it.copy(quantity = it.quantity + 1)
                            else it
                        }*/
                    },
                    onDecrease = {
                        /*cartItems = cartItems.map {
                            if (it.id == item.id && it.quantity > 1)
                                it.copy(quantity = it.quantity - 1)
                            else it
                        }*/
                    }
                )
            }
        }

        Divider()

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "$${"%.2f".format(totalPrice)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* Checkout */ }
        ) {
            Text("Checkout")
        }
    }
}
