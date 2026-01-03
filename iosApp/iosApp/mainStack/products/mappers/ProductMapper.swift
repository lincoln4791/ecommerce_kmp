//
//  ProductMapper.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 25/12/25.
//

import sharedKit
struct ProductMapper {

    static func mapProductsUiModelFromProductsDataItemList(
        items: [ProductsDataItem]
    ) -> [ProductUiModel] {

        items.map { item in
            ProductUiModel.fromProductDataItem(productItem: item)
        }
    }
}
