package com.example.ecommerce.ui.views.order.model

import com.example.shared.data.model.responses.order.OrderData

data class OrderDataUiModel(
    val id: Int?,
    val userId: Int?,
    val items: List<OrderDataItemUiModel>,
    val paymentMethod: String?,
    val totalAmount: Double?,
    val deliveryStatus: String?,
){
    companion object{
        fun fromOrderData(orderData : OrderData) : OrderDataUiModel{
            return OrderDataUiModel(
                id = orderData.id,
                userId = orderData.userId,
                items = orderData.items.map { it-> OrderDataItemUiModel.fromOrderDaraItem(it)}.toList(),
                paymentMethod = orderData.paymentMethod,
                totalAmount = orderData.totalAmount,
                deliveryStatus = orderData.deliveryStatus,

            )
        }
    }
}