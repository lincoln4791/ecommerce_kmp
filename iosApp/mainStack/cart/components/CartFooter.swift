//
//  CartFooter.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 28/12/25.
//

import SwiftUI

struct CartFooter: View {
    let total: Double
    let onPlaceOrder: () -> Void
    var body: some View {
        VStack {
            if(total>0){
                HStack {
                    Text("Total")
                    Spacer()
                    Text("৳ \(total)")
                        .font(.title3)
                        .fontWeight(.bold)
                }
            }
            


            Button("Place Order") {
                onPlaceOrder()
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(Color.blue)
            .foregroundColor(.white)
            .cornerRadius(10)
            .disabled(total<1.0)
            .opacity(total < 1.0 ? 0.4 : 1.0)
        }
        .padding()
    }
}
