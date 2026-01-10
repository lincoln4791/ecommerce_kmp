package com.example.ecommerce.ui.views.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerce.ui.views.products.model.ProductUiModel
import com.example.shared.data.model.responses.product.ProductsDataItem

@Composable
fun ProductListWidget(products: List<ProductUiModel>,onItemClick:(product : ProductUiModel)->Unit,onAddToCart:(product : ProductUiModel)->Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        userScrollEnabled = true, // 🔑 VERY IMPORTANT
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products,
            key = { it.id }
        ) { product ->


            ProductCardWidget(
                product = product,
                onClick = { onItemClick(product) },
                onAddToCart = { onAddToCart(product) }
            )
        }
    }
}
