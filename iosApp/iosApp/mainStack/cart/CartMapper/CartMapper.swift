//
//  ProductMapper.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 25/12/25.
//

import sharedKit
struct CartMapper {
    
    static func mapToCartItemsUiModel(
        items: [CartData]
    ) -> [CartDataUiModel] {
        
        items.map { item in
            CartDataUiModel.fromCartData(cartData:item)
        }
    }
}
