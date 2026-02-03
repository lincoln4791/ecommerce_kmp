import SwiftUI

struct ProductsView: View {
    @EnvironmentObject var authVM : AuthViewModel
    @StateObject var viewModel = ProductsViewModel()
    @Binding var path: NavigationPath
    @State private var showToast = false
    @State private var toastMessage : String? = ""

    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        VStack(spacing: 0) {
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
                LoaderOverlay(text: "Products loading..." )
            }
        }
        .onReceive(viewModel.$errorMessage) { message in
            if let message, !message.isEmpty {
                toastMessage = message
                showToast = true
            }
        }
        .errorAlert(errorMessage: toastMessage, isShow: $showToast)
        
    }
}
