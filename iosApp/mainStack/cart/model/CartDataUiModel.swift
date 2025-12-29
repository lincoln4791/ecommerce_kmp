//
//  CartItemUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 28/12/25.
//

import sharedKit

struct CartDataUiModel: Identifiable {
    let id: Int32

    let productId: Int32
    let productName: String
    let brandName: String
    let categoryName: String

    let price: Double
    var quantity: Int
    let stock: Int
}

extension CartDataUiModel {

    static func fromCartData(cartData: CartData) -> CartDataUiModel {
        CartDataUiModel(
            id: cartData.id,
            productId: cartData.product.id,
            productName: cartData.product.name,
            brandName: cartData.product.model.brand.name,
            categoryName: cartData.product.category.name,
            price: cartData.product.price,
            quantity: Int(cartData.quantity),
            stock: Int(cartData.product.stock)
        )
    }
    
    static func toCartBulkItem(cartData: CartDataUiModel) -> CartBulkUpdateItem {
        CartBulkUpdateItem(
            productId: Int32(cartData.productId), quantity: Int32(cartData.quantity)
        )
    }
}

