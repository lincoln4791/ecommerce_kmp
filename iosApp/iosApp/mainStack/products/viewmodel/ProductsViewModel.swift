//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
import sharedKit
@MainActor
class ProductsViewModel: ObservableObject {
    
    @Published var errorMessage : String? = nil
    @Published var isLoading = false
    @Published var products: [ProductUiModel] = []
    
    
    // 🔹 Filters
    @Published var selectedBrand: String? = nil
    @Published var selectedCategory: String? = nil
    @Published var searchQuery: String = ""

    // 🔹 Derived lists (for filter UI)
    var brands: [String] {
        Array(Set(products.compactMap { $0.model.brand.name })).sorted()
    }

    var categories: [String] {
        Array(Set(products.compactMap { $0.category.name })).sorted()
    }

    // 🔹 Filtered products (THIS is what UI uses)
    var filteredProducts: [ProductUiModel] {
        products.filter { product in
            let brandMatch =
            selectedBrand == nil || product.model.brand.name == selectedBrand

            let categoryMatch =
            selectedCategory == nil || product.category.name == selectedCategory
            
            let searchMatch =
                           searchQuery.isEmpty ||
                           (product.name.localizedCaseInsensitiveContains(searchQuery) == true)

            return brandMatch && categoryMatch && searchMatch
        }
    }
    
    private let controller = ControllerProvider.shared.getProductsController()
    
    init(){
        getProducts()
    }
    
    
    func getProducts() {
        isLoading = true

        controller.getProducts { (result: GetProductsResponseBase?, error: Error?) in
            DispatchQueue.main.async {
                self.isLoading = false

                if let error {
                    self.errorMessage = error.localizedDescription
                    return
                }

                guard let result else {
                    self.errorMessage = "Unknown error"
                    return
                }

                if let success = result as? GetProductsResponseBase.Success {
                    let items = success.data.data
                    self.products = ProductMapper.mapProductsUiModelFromProductsDataItemList(items : items!.items)
                    return
                }

                if let errorResult = result as? GetProductsResponseBase.Error {
                    self.errorMessage = errorResult.error.toUiMessage()
                    return
                }

                self.errorMessage = "Unknown error"
            }
        }
    }
}
