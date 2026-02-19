//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
import sharedKit
@MainActor
class ProductDetailsViewModel: ObservableObject {
    
    @Published var errorMessage = ""
    @Published var isLoading = false
    @Published var products: [ProductUiModel] = []

    private let controller = ControllerProvider.shared.getCartController()
 
    func addToCart(addToCartRequest:AddToCartRequest) {
        errorMessage = ""
        isLoading = true

        controller.addToCart(addToCartRequest: addToCartRequest) { result, error in
            DispatchQueue.main.async {
                self.isLoading = false

                if let error = error {
                    self.errorMessage = error.localizedDescription
                    return
                }

                guard let result = result else {
                    self.errorMessage = "Unknown error"
                    return
                }

                switch result {
                case let success as AddToCartResponseBase.Success:
                    self.errorMessage = "Success"

                case let error as AddToCartResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
}
