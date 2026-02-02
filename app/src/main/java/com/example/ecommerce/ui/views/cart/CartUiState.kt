package com.example.ecommerce.ui.views.cart

import com.example.ecommerce.ui.views.cart.model.CartDataUiModel
import com.example.shared.domain.model.AppError

data class CartUiState(
    val isLoading: Boolean = false,
    val cart: List<CartDataUiModel> = emptyList(),
    val error: AppError? = null,
)