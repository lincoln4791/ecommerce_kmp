//
//  ProductCardView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 24/12/25.
//


import SwiftUI

struct ProductCardWidget: View {
    
    let product: ProductUiModel

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            ZStack {
                RoundedRectangle(cornerRadius: 12)
                    .fill(Color.gray.opacity(0.15))

                Image(systemName: FakeDataUtils.productIcons.randomElement() ?? "iphone")
                    .resizable()
                    .scaledToFit()
                    .frame(height: 60)
                    .foregroundColor(.pink)
            }
            .frame(height: 120)

            // 🏷 Product Name
            Text(product.name)
                .font(.headline)
                .lineLimit(2)

            // 🏢 Brand
            Text(product.model.brand.name)
                .font(.subheadline)
                .foregroundColor(.secondary)

            // 💰 Price + Stock
            HStack {
                Text(product.priceText)
                    .font(.headline)
                    .foregroundColor(.green)

                Spacer()

                Text(product.stockText)
                    .font(.caption)
                    .foregroundColor(product.stockColor)
            }

        }
        .padding()
        .background(
            RoundedRectangle(cornerRadius: 16)
                .fill(Color(.systemBackground))
                .shadow(color: .black.opacity(0.08), radius: 6, x: 0, y: 4)
        )
    }
}
