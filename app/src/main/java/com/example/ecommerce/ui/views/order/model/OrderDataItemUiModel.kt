package com.example.ecommerce.ui.views.order.model

import com.example.ecommerce.ui.views.products.model.ProductUiModel
import com.example.shared.data.model.responses.order.OrderDataItem

data class OrderDataItemUiModel(
    val id: Int?,
    val price: Double?,
    val product: ProductUiModel?,
    val quantity: Int?
){
    companion object{
        fun fromOrderDaraItem(orderDataItem: OrderDataItem) : OrderDataItemUiModel{
            return OrderDataItemUiModel(
                id = orderDataItem.id,
                price = orderDataItem.price,
                product = ProductUiModel.fromProductDataItem(orderDataItem.product),
                quantity = orderDataItem.quantity
            )
        }
    }
}