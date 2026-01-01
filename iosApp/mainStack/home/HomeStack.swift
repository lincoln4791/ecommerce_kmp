//
//  FeedStack.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI

struct HomeStack: View {
    @EnvironmentObject var authVM : AuthViewModel
    @State private var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            HomeView(path: $path)
                .navigationDestination(for: HomeRoute.self) { route in
                    
                    switch route {
                    case .allProducts:
                        ProductsView(path: $path)
                        
                    case .productDetails(let product) :
                        ProductDetailsView(path : $path, product : product)
                        
                        
                    case .cart:
                        CartsView(path: $path)
                        
                    case .orderHiostory:
                        OrderHistoryView(path: $path)
                        
                    case .orderDetails(let order):
                        OrderHistoryDetailsView(path: $path,order: order)
           

                    }
                }
        }
    }
}

enum HomeRoute: Hashable {
    case allProducts
    case productDetails(product:ProductUiModel)
    case cart
    case orderHiostory
    case orderDetails(orderItemUiModel : MyOrderDataUiModel)
}
