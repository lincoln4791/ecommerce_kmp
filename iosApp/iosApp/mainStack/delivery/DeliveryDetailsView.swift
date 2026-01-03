//
//  FeedView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//


import SwiftUI
struct DeliveryDetailsView: View {
    @Binding var path: NavigationPath
    let orderId: String
    var body: some View {
        VStack {
            Text("Delivery Details View for Order ID: \(orderId)")
            Button("Edit Data"){
                path.append(DeliveryRoute.deliveryDetailsEdit(orderId: "1"))
            }
        }
        
    }
}
