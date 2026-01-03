//
//  ProductMapper.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 25/12/25.
//

import sharedKit
struct OrderMapper {
    
    static func mapToOrderItemsUiModelFromOrderDataList(
        items: [OrderData]
    ) -> [MyOrderDataUiModel] {
        items.map { item in
            MyOrderDataUiModel.fromOrderData(orderData: item)
        }
    }
}
