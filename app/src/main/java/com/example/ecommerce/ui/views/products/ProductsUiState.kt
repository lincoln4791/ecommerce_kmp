package com.example.ecommerce.ui.views.products

import com.example.shared.data.model.responses.product.ProductsDataItem
import com.example.shared.domain.model.AppError

data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<ProductsDataItem> = emptyList(),
    val error: AppError? = null,

    // 🔍 Filters
    val searchQuery: String = "",
    val selectedCategory: String? = null,
    val selectedBrand: String? = null
)