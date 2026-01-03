package com.example.shared.data.model.requests.cart

fun createCartBulkUpdateRequest(
    items: List<CartBulkUpdateItem>
): CartBulkUpdateRequest {
    return CartBulkUpdateRequest(items)
}