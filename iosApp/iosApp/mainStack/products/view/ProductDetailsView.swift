import SwiftUI
import sharedKit

struct ProductDetailsView: View {
    @Binding var path: NavigationPath
    let product: ProductUiModel
    @StateObject var viewModel = ProductDetailsViewModel()
    let productImage = FakeDataUtils.productIcons.randomElement() ?? "iphone"


    var body: some View {
        
        ZStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 20) {

                    // 🖼 Product Image
                    ZStack {
                        RoundedRectangle(cornerRadius: 16)
                            .fill(Color.gray.opacity(0.15))
                            .frame(height: 220)

                        Image(systemName: productImage)
                            .resizable()
                            .scaledToFit()
                            .frame(height: 100)
                            .foregroundColor(.gray)
                    }

                    // 🏷 Name
                    Text(product.name ?? "Unnamed Product")
                        .font(.title2)
                        .fontWeight(.semibold)

                    // 🏢 Brand
                    Text(product.model.brand.name ?? "Unknown Brand")
                        .font(.subheadline)
                        .foregroundColor(.secondary)

                    // 💰 Price + Stock
                    HStack {
                        Text(product.priceText)
                            .font(.title3)
                            .fontWeight(.bold)
                            .foregroundColor(.green)

                        Spacer()

                        Text(product.stockText)
                            .font(.caption)
                            .foregroundColor(product.stockColor)
                            .padding(.horizontal, 10)
                            .padding(.vertical, 6)
                            .background(product.stockColor.opacity(0.15))
                            .cornerRadius(12)
                    }

                    Divider()

                    // ℹ️ Product Information
                    VStack(alignment: .leading, spacing: 12) {
                        infoRow(title: "Category", value: product.category.name)
                        infoRow(title: "Model", value: product.model.name)
                        infoRow(title: "Status", value: product.productStatus)
                    }

                    Divider()

                    // 📝 Description
                    if let description = product.model.description, !description.isEmpty {
                        Text("Description")
                            .font(.headline)

                        Text(description)
                            .font(.body)
                            .foregroundColor(.secondary)
                    }

                    // 🛒 Add to Cart
                    Button(action: {
                        let request = AddToCartRequest(productId: Int32(product.id), quantity:Int32(1))
                        viewModel.addToCart(addToCartRequest: request)
                    }) {
                        if viewModel.isLoading {
                            ProgressView()
                                .progressViewStyle(CircularProgressViewStyle(tint: .white))
                        } else {
                            Text("Add to Cart")
                                .fontWeight(.semibold)
                        }
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(product.stock == 0 || viewModel.isLoading ? Color.gray : Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(14)
                    .disabled(product.stock == 0 || viewModel.isLoading)
                    
                    // 🛒 Add to Cart
                    Button(action: {
                       // viewModel.addToCart(addToCartRequest: <#T##AddToCartRequest#>(product: <#T##Product#>, quantity: <#T##Int#>))
                    }) {
                        if viewModel.isLoading {
                            ProgressView()
                                .progressViewStyle(CircularProgressViewStyle(tint: .white))
                        } else {
                            Text("Buy")
                                .fontWeight(.semibold)
                        }
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(product.stock == 0 || viewModel.isLoading ? Color.gray : Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(14)
                    .disabled(product.stock == 0 || viewModel.isLoading)
                }
                .padding()
            }
            .navigationTitle("Product Details")
            .navigationBarTitleDisplayMode(.inline)
            
            if viewModel.isLoading {
                Color.black.opacity(0.3)
                    .ignoresSafeArea()

                ProgressView("Adding to cart...")
                    .padding()
                    .background(.ultraThinMaterial)
                    .cornerRadius(12)
            }
            
        }
        

    }

    // 🔹 Reusable Info Row
    private func infoRow(title: String, value: String?) -> some View {
        HStack {
            Text(title)
                .foregroundColor(.secondary)
            Spacer()
            Text(value ?? "-")
                .fontWeight(.medium)
        }
    }
}

