//
//  ProductMapper.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 25/12/25.
//

import sharedKit
struct ProductMapper {

    static func mapProductsUiModel(
        items: [ProductsDataItem]
    ) -> [ProductUiModel] {

        items.map { item in
            ProductUiModel(
                id: Int(item.id),
                name: item.name,
                price: item.price,
                stock: Int(item.stock),
                productStatus: item.productStatus,

                categoryId: Int(item.category.id),
                categoryName: item.category.name,
                categoryDescription: item.category.description,

                modelId: Int(item.model.id),
                modelName: item.model.name,
                modelDescription: item.model.description,

                brandId: Int(item.model.brand.id),
                brandName: item.model.brand.name,
                brandDescription: item.model.brand.description
            )
        }
    }
}
