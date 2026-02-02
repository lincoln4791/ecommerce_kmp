package com.example.ecommerce.ui.views.order

import com.example.ecommerce.ui.views.cart.model.CartDataUiModel
import com.example.ecommerce.ui.views.order.model.OrderDataUiModel
import com.example.shared.domain.model.AppError

data class OrderUiState(
    val isLoading: Boolean = false,
    val order: List<OrderDataUiModel> = emptyList(),
    val error: AppError? = null,
)