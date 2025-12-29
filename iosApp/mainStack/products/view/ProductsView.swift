import SwiftUI

struct ProductsView: View {

    @StateObject var viewModel = ProductsViewModel()
    @Binding var path: NavigationPath

    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        VStack(spacing: 0) {

            // 🔹 Top loading bar
//            if viewModel.isLoading {
//                ProgressView()
//                    .progressViewStyle(.linear)
//                    .padding(.horizontal)
//                    .padding(.top, 4)
//            }

            ProductFilterBarWidget(viewModel: viewModel)
                .padding(.top, viewModel.isLoading ? 8 : 0)

            Spacer().frame(height: 30)

            ScrollView(.vertical, showsIndicators: false) {
                LazyVGrid(columns: columns, spacing: 16) {
                    ForEach(viewModel.filteredProducts) { item in
                        ProductCardWidget(product: item)
                            .onTapGesture {
                                path.append(HomeRoute.productDetails(product: item))
                            }
                    }
                }
            }
            .safeAreaInset(edge: .bottom) {
                Color.clear.frame(height: 16)
            }
            .refreshable {
                viewModel.getProducts()
            }
        }
        .padding()
        .navigationTitle("Products")
        .toolbar(.hidden, for: .tabBar)
        .searchable(
            text: $viewModel.searchQuery,
            placement: .navigationBarDrawer(displayMode: .always),
            prompt: "Search products"
        )
        .onAppear {
            viewModel.getProducts()
        }
        .overlay {
            if viewModel.isLoading {
                ZStack {
                    Color.black.opacity(0.1).ignoresSafeArea()
                    ProgressView("Loading products...")
                        .padding(24)
                        .background(.ultraThinMaterial)
                        .cornerRadius(12)
                }
            }
        }
        
    }
}
