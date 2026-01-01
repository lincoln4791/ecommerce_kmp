//
//  CategoryUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import sharedKit
struct CategoryUiModel: Identifiable, Codable, Hashable {
    let id: Int
    let name: String
    let description: String
}
extension CategoryUiModel {
    static func fromProductDataItem(categotyItem: ProductCategory) -> CategoryUiModel {
        CategoryUiModel(
            id: Int(categotyItem.id),
            name: categotyItem.name,
            description: categotyItem.description
        )
    }
}
