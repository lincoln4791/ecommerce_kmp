//
//  ProductModelUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import sharedKit
struct ProductModelUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let name: String
    let description: String?
    let brand: BrandUiModel
}

extension ProductModelUiModel {
    static func fromProductModel(item: ProductModel) -> ProductModelUiModel {
        ProductModelUiModel(
            id: Int(item.id),
            name: item.name,
            description: item.description,
            brand: BrandUiModel.fromBrandUiModel(item: item.brand)
        )
    }
}
