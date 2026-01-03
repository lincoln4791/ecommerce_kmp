package com.example.ecommerce.ui.theme.products.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.shared.data.model.responses.product.ProductsDataItem

@Composable
fun ProductListWidget(products: List<ProductsDataItem>) {
    LazyColumn {
        items(products) { item ->
            ProductItem(item)
        }
    }
}
