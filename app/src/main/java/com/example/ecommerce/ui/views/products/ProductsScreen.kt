package com.example.ecommerce.ui.views.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ecommerce.ui.views.products.components.ProductListWidget
import com.example.ecommerce.ui.views.products.components.ProductsDropdownWidget
import com.example.ecommerce.ui.views.products.model.ProductUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreen(
    navController: NavController,
    viewModel: ProductViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val products by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val brands by viewModel.brands.collectAsStateWithLifecycle()

    Column(
        Modifier.padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {

            // 🔍 Search bar
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search products") },
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth()
            ) {
                // 🏷 Category dropdown
                ProductsDropdownWidget(
                    label = "Category",
                    items = categories,
                    selectedItem = uiState.selectedCategory,
                    onItemSelected = viewModel::onCategorySelected,
                    onClear = { viewModel.onCategorySelected(null) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(8.dp))

                // 🏭 Brand dropdown
                ProductsDropdownWidget(
                    label = "Brand",
                    items = brands,
                    selectedItem = uiState.selectedBrand,
                    onItemSelected = viewModel::onBrandSelected,
                    onClear = { viewModel.onBrandSelected(null) },
                    modifier = Modifier.weight(1f)
                )
            }

        }

        ProductListWidget(
            products = products.map { ProductUiModel.fromProductDataItem(it) }.toList(),
            onItemClick = {},
            onAddToCart = {}
        )
    }
}

