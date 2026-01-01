//
//  BrandUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import sharedKit
struct BrandUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let name: String
    let description: String?
}

extension BrandUiModel {
    static func fromBrandUiModel(item: ProductBrand) -> BrandUiModel {
        BrandUiModel(
            id: Int(item.id),
            name: item.name,
            description: item.description
        )
    }
}
