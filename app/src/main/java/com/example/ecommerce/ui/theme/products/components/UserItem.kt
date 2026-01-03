package com.example.ecommerce.ui.theme.products.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shared.data.model.responses.product.ProductsDataItem

@Composable
fun ProductItem(item: ProductsDataItem) {
    Text(
        text = item.name,
        modifier = Modifier.padding(16.dp)
    )
}
