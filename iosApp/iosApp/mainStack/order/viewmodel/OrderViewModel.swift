//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
import sharedKit
@MainActor
class OrderViewModel: ObservableObject {
    @Published var errorMessage : String?
    @Published var myOrderItems: [MyOrderDataUiModel] = []
    @Published var placeOrderState: UiState<Void> = .idle
    @Published var getMyOrderState: UiState<Void> = .loading

    private let controller = OrderController(userSession: UserSessionProvider.shared.userSession)
    
    func placeOrderFromCart() {
        errorMessage = ""
        placeOrderState = .loading

        controller.placeOrderFromCart(){ result, error in
            DispatchQueue.main.async {
                
                
                if let error = error {
                    self.errorMessage = error.localizedDescription
                    self.placeOrderState = .error(error.localizedDescription)
                    return
                }

                guard let result = result else {
                    self.errorMessage = "Unknown error"
                    self.placeOrderState = .error("Unknown Error")
                    return
                }

                switch result {
                case let success as PlaceOrderFromCartResponseBase.Success:
                
                    self.placeOrderState = .success(())
                    

                case let error as PlaceOrderFromCartResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()
                    self.placeOrderState = .error(error.error.toUiMessage())

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
    
    func getOrders() {
        getMyOrderState = .loading

        controller.getMyOrders(){ result, error in
            DispatchQueue.main.async {
                if let error = error {
                    self.getMyOrderState = .error("error")
                    self.errorMessage = "error"
                    return
                }

                guard let result = result else {
                    self.getMyOrderState = .error("error")
                    self.errorMessage = "error"
                    return
                }

                switch result {
    
                case let success as GetMyOrdersResponseBase.Success:
                    guard let orders = success.data.data else {
                        self.getMyOrderState = .error("List is Empty")
                        return
                    }

                    self.myOrderItems = OrderMapper.mapToOrderItemsUiModelFromOrderDataList(items: orders)
                    self.getMyOrderState = .success(())

                case let error as GetMyOrdersResponseBase.Error:
                    self.getMyOrderState = .error(error.error.toUiMessage())
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                    self.getMyOrderState = .error("Unknown Error")
                }
            }
        }
    }

}
//
//  CartViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 28/12/25.
//

