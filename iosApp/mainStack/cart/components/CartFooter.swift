//
//  CartFooter.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 28/12/25.
//

import SwiftUI

struct CartFooter: View {
    let total: Double

    var body: some View {
        VStack {
            HStack {
                Text("Total")
                Spacer()
                Text("৳ \(total)")
                    .font(.title3)
                    .fontWeight(.bold)
            }

            Button("Checkout") {
                // Navigate to checkout
            }
            .frame(maxWidth: .infinity)
            .padding()
            .background(Color.blue)
            .foregroundColor(.white)
            .cornerRadius(10)
        }
        .padding()
    }
}
