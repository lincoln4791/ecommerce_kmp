//
//  MyOrderUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import sharedKit

struct MyOrderDataUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let userId: Int
    let totalAmount: Double
    let items: [MyOrderItemUiModel]
    let deliveryStatus: String
    let paymentMethod: String
}

extension MyOrderDataUiModel {

    static func fromOrderData(orderData: OrderData) -> MyOrderDataUiModel {
        MyOrderDataUiModel(
            id: Int(orderData.id),
            userId:Int(orderData.userId),
            totalAmount: orderData.totalAmount ,
            items: orderData.items.map(MyOrderItemUiModel.fromOrderItem),
            deliveryStatus:orderData.deliveryStatus,
            paymentMethod: orderData.paymentMethod
        )
    }
    
}


struct MyOrderItemUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let product: ProductUiModel
    let quantity: Int
    let price: Double
}

extension MyOrderItemUiModel {

    static func fromOrderItem(orderItem: OrderItem) -> MyOrderItemUiModel {
        MyOrderItemUiModel(
            id: Int(orderItem.id),
            product: ProductUiModel.fromProductDataItem(productItem: orderItem.product),
            quantity:Int(orderItem.quantity),
            price: orderItem.price
        )
    }
    
}


