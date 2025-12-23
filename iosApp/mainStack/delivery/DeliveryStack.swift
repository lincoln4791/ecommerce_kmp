//
//  DeliveryStack.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI
struct DeliveryStack: View {

    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            DeliveryListView(path: $path)
                .navigationDestination(for: DeliveryRoute.self) { route in
                    switch route {
                    case .deliveryDetails(let orderId):
                        DeliveryDetailsView(path: $path, orderId : orderId)
                        
                    case .deliveryDetailsEdit(let orderId):
                        DeliveryDetailsEditView(orderId : orderId,)
                        
                    }
                }
        }
    }
}

enum DeliveryRoute: Hashable {
    case deliveryDetails(orderId: String)
    case deliveryDetailsEdit(orderId : String)
}
