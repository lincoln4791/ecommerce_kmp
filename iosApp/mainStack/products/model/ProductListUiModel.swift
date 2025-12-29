//
//  ProductUiModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 24/12/25.
//

import Foundation
import SwiftUI

struct ProductListUiModel {
    let items: [ProductUiModel]
    let total: Int?
    let page: Int?
}


struct ProductUiModel: Identifiable,Hashable {
    // MARK: - Core
    let id: Int
    let name: String?
    let price: Double?
    let stock: Int?
    let productStatus: String?

    // MARK: - Category
    let categoryId: Int?
    let categoryName: String?
    let categoryDescription: String?

    // MARK: - Model
    let modelId: Int?
    let modelName: String?
    let modelDescription: String?

    // MARK: - Brand
    let brandId: Int?
    let brandName: String?
    let brandDescription: String?
    
//    func hash(into hasher: inout Hasher) {
//        hasher.combine(id)
//    }
    
}


extension ProductUiModel {

    var priceText: String {
        guard let price else { return "--" }
        return "৳ \(Int(price))"
    }

    var stockText: String {
        guard let stock else { return "N/A" }
        return stock > 0 ? "In stock (\(stock))" : "Out of stock"
    }

    var stockColor: Color {
        guard let stock else { return .gray }
        return stock > 0 ? .green : .red
    }
}

