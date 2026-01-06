import SwiftUI
import sharedKit

struct CartsView: View {

    @StateObject private var viewModel = CartViewModel()
    @StateObject private var orderViewModel = OrderViewModel()
    @Binding var path: NavigationPath
    
    @State private var showToast = false
    @State private var toastMessage : String? = ""

    var body: some View {
        VStack {
            if viewModel.isLoading {
                ProgressView()
            }
            
            if viewModel.cartItems.isEmpty {
                Text(viewModel.errorMessage ?? "No items in cart")
            }
            
            else {
                List {
                    ForEach(viewModel.cartItems) { item in
                        CartItemRow(
                            item: item,
                            onUpdate: { qty in
                                viewModel.updateQuantity(item: item, quantity: qty)
                            },
                            onDelete: {
                                viewModel.removeItem(item: item)
                            }
                        )
                    }
                }

                CartFooter(total:viewModel.cartItems.reduce(0) { $0 + $1.price * Double($1.quantity) }) {
                    orderViewModel.placeOrderFromCart()
                }
          
            }
        }
        .onAppear {
            viewModel.getCart()
        }
        .navigationTitle("My Cart")
        .overlay {
            if (orderViewModel.placeOrderState.isLoading || viewModel.isLoading ) {
                ZStack {
                    Color.black.opacity(0.1).ignoresSafeArea()
                    ProgressView("Loading products...")
                        .padding(24)
                        .background(.ultraThinMaterial)
                        .cornerRadius(12)
                }
            }
        }
//        .overlay(alignment: Alignment.bottom) {
//            if showToast {
//                ToastView(message: toastMessage)
//                    .padding(Edge.Set.bottom, 40)
//                    .transition(
//                        .move(edge: Edge.bottom)
//                            .combined(with: .opacity)
//                    )
//            }
//        }
        .onReceive(orderViewModel.$placeOrderState) { state in
            switch state {
            case .loading:
                print("Show loader")

            case .success:
                path.removeLast()

            case .error(let message):
                toastMessage = message.isEmpty ? "Something went wrong" : message
                showToast = true

//                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
//                    withAnimation {
//                        showToast = false
//                    }
//                }

            case .idle:
                break
            }
        }
        .onReceive(viewModel.$errorMessage) { message in
            if let message, !message.isEmpty{
                toastMessage = message
                showToast = true
            }
        }
        .errorAlert(errorMessage: toastMessage,isShow: $showToast)
        
    }
}
