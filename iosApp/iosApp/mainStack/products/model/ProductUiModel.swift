//
//  ProductUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import sharedKit
struct ProductUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let name: String
    let price: Double
    let stock: Int
    let category: CategoryUiModel
    let model: ProductModelUiModel
    let productStatus: String
}

extension ProductUiModel {

    static func fromProductDataItem(productItem: ProductsDataItem) -> ProductUiModel {
        ProductUiModel(
            id: Int(productItem.id),
            name: productItem.name,
            price: productItem.price,
            stock: Int(productItem.stock),
            category: CategoryUiModel.fromProductDataItem(categotyItem: productItem.category),
            model: ProductModelUiModel.fromProductModel(item: productItem.model),
            productStatus: productItem.productStatus
        )
    }
    
}


