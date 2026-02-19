package com.example.ecommerce.ui.views.cart.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerce.ui.views.cart.model.CartDataUiModel

@Composable
fun CartItemRowWidget(
    item: CartDataUiModel,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = item.product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "$${item.product.price}",
                color = Color(0xFF388E3C),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDecrease) {
                    Icon(Icons.Default.Delete, contentDescription = "Decrease")
                }

                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 16.sp
                )

                IconButton(onClick = onIncrease) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
            }
        }
    }
}
