//
//  OrderCardView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//


import SwiftUI
struct OrderHistpryListWidget: View {

    let order: MyOrderDataUiModel

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {

            HStack {
                Text("Order #\(order.id)")
                    .font(.headline)

                Spacer()

                Text(order.deliveryStatus)
                    .font(.caption)
                    .padding(.horizontal, 8)
                    .padding(.vertical, 4)
                    .background(Color.orange.opacity(0.15))
                    .cornerRadius(6)
            }

            Text("Items: \(order.items.count)")
                .font(.subheadline)
                .foregroundColor(.secondary)

            Text("Total: ৳\(order.totalAmount)")
                .font(.headline)
        }
        .padding()
        .background(.ultraThinMaterial)
        .cornerRadius(12)
    }
}

