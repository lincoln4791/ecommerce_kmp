package com.example.ecommerce.ui.views.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ecommerce.ui.views.products.components.ProductListWidget
import com.example.ecommerce.ui.views.products.components.ProductsDropdownWidget
import com.example.ecommerce.ui.views.products.model.ProductUiModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreen(
    navController: NavHostController,
    viewModel: ProductViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val products by viewModel.filteredProducts.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val brands by viewModel.brands.collectAsStateWithLifecycle()

    ProductsScreenUi(
        isLoading = uiState.isLoading,
        searchQuery = uiState.searchQuery,
        products = products.map { ProductUiModel.fromProductDataItem(it) },
        categories = categories,
        brands = brands,
        selectedCategory = uiState.selectedCategory,
        selectedBrand = uiState.selectedBrand,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        onCategorySelected = viewModel::onCategorySelected,
        onBrandSelected = viewModel::onBrandSelected,
        onItemClick = { /* navigate */ },
        onAddToCart = { /* add */ }
    )
}

@Composable
fun ProductsScreenUi(
    isLoading: Boolean,
    searchQuery: String,
    products: List<ProductUiModel>,
    categories: List<String>,
    brands: List<String>,
    selectedCategory: String?,
    selectedBrand: String?,
    onSearchQueryChange: (String) -> Unit,
    onCategorySelected: (String?) -> Unit,
    onBrandSelected: (String?) -> Unit,
    onItemClick: (ProductUiModel) -> Unit,
    onAddToCart: (ProductUiModel) -> Unit
) {
    Column(Modifier.padding(12.dp)) {

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Gray)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search products") },
                singleLine = true
            )

            Spacer(Modifier.height(8.dp))

            Row(Modifier.fillMaxWidth()) {

                ProductsDropdownWidget(
                    label = "Category",
                    items = categories,
                    selectedItem = selectedCategory,
                    onItemSelected = onCategorySelected,
                    onClear = { onCategorySelected(null) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(Modifier.width(8.dp))

                ProductsDropdownWidget(
                    label = "Brand",
                    items = brands,
                    selectedItem = selectedBrand,
                    onItemSelected = onBrandSelected,
                    onClear = { onBrandSelected(null) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        ProductListWidget(
            products = products,
            onItemClick = onItemClick,
            onAddToCart = onAddToCart
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    MaterialTheme {
        ProductsScreenUi(
            isLoading = false,
            searchQuery = "iPhone",
            products = ProductUiModel.getDemoProductsList(),
            categories = listOf("Phones", "Laptops", "Accessories"),
            brands = listOf("Apple", "Samsung", "Xiaomi"),
            selectedCategory = "Phones",
            selectedBrand = "Apple",
            onSearchQueryChange = {},
            onCategorySelected = {},
            onBrandSelected = {},
            onItemClick = {},
            onAddToCart = {}
        )
    }
}

