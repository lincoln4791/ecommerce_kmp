//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
import sharedKit
@MainActor
class CartViewModel: ObservableObject {
    
    @Published var errorMessage : String?
    @Published var isLoading = false
    @Published var cartItems: [CartDataUiModel] = []
    private var updateQuantityTask: Task<Void, Never>?

    private let controller = CartController(userSession: UserSessionProvider.shared.userSession)
 
    func addToCart(addToCartRequest:AddToCartRequest) {
        isLoading = true

        controller.addToCart(addToCartRequest: addToCartRequest, ){ result, error in
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
                    self.errorMessage = nil

                case let error as AddToCartResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
    
    
    func getCart() {
        isLoading = true
        controller.getCart(){ result, error in
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
                case let success as GetCartResponseBase.Success:
                    self.cartItems = CartMapper.mapToCartItemsUiModel(items: success.data.data!)
                    self.errorMessage = nil

                case let error as GetCartResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
    
    func updateQuantity(item: CartDataUiModel, quantity: Int) {

        // 1️⃣ Update UI immediately
        if let index = cartItems.firstIndex(where: { $0.productId == item.productId }) {
            cartItems[index].quantity = quantity
        }

        // 2️⃣ Cancel previous debounce task
        updateQuantityTask?.cancel()

        // 3️⃣ Debounce API call
        updateQuantityTask = Task {
            do {
                try await Task.sleep(nanoseconds: 400_000_000) // ✅ 400 ms
            } catch {
                return // ✅ Task was cancelled → stop here
            }

            // ✅ Only LAST task reaches here
            await syncQuantityToServer()
        }
    }


    // MARK: - API sync (called once)
    private func syncQuantityToServer(
    
    ) async {
        print("Sync Called")
        
        var cartBulkUpdateItems : [CartBulkUpdateItem] = []
        
        cartItems.forEach { item in
            cartBulkUpdateItems.append(CartDataUiModel.toCartBulkItem(cartData: item))
        }

        let request = CreateCartBulkUpdateRequestKt.createCartBulkUpdateRequest(
            items: cartBulkUpdateItems
        )
        
        controller.cartBulkUpdate(cartBulkUpdateRequest: request) { result, error in
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
                case let success as CartBulkUpdateResponseBase.Success:
                    self.errorMessage = "Success"
                    

                case let error as CartBulkUpdateResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }

    }

    // MARK: - Remove item (still immediate)
    func removeItem(item: CartDataUiModel) {

        // Optimistic UI remove
        cartItems.removeAll { $0.productId == item.productId }

//        controller.removeItem(productId: item.productId) { _, error in
//            if let error {
//                DispatchQueue.main.async {
//                    self.errorMessage = error.localizedDescription
//                }
//            }
//        }
    }
    
}


