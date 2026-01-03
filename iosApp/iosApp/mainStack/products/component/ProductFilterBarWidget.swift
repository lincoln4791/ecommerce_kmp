//
//  ProductFilterBar.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 24/12/25.
//

import SwiftUI

struct ProductFilterBarWidget: View {

    @ObservedObject var viewModel: ProductsViewModel

    var body: some View {
        HStack(spacing: 12) {

            // 🏷 Category Filter
            Menu {
                Button("All Categories") {
                    viewModel.selectedCategory = nil
                }

                ForEach(viewModel.categories, id: \.self) { category in
                    Button(category) {
                        viewModel.selectedCategory = category
                    }
                }
            } label: {
                filterChip(
                    title: viewModel.selectedCategory ?? "Category"
                )
            }

            // 🏢 Brand Filter
            Menu {
                Button("All Brands") {
                    viewModel.selectedBrand = nil
                }

                ForEach(viewModel.brands, id: \.self) { brand in
                    Button(brand) {
                        viewModel.selectedBrand = brand
                    }
                }
            } label: {
                filterChip(
                    title: viewModel.selectedBrand ?? "Brand"
                )
            }

            Spacer()
        }
    }

    // 🔹 Chip UI
    private func filterChip(title: String) -> some View {
        Text(title)
            .font(.subheadline)
            .padding(.horizontal, 12)
            .padding(.vertical, 8)
            .background(Color.gray.opacity(0.15))
            .cornerRadius(20)
    }
}
