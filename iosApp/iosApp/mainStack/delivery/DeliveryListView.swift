//
//  FeedView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//


import SwiftUI
struct DeliveryListView: View {
    @Binding var path: NavigationPath
    var body: some View {
        VStack{
            Text("Delivery List View")
            Button("Go To Details"){
                path.append(DeliveryRoute.deliveryDetails(orderId: "1"))
            }
        }
     
    }
}
