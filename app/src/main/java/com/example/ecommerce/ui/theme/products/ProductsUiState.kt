package com.example.ecommerce.ui.theme.products

import com.example.shared.data.model.responses.product.ProductsDataItem
import com.example.shared.domain.model.AppError

data class ProductsUiState(
    val isLoading: Boolean = false,
    val products: List<ProductsDataItem> = emptyList(),
    val error: AppError? = null
)