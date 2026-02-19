import SwiftUI
import sharedKit

struct OrderHistoryView: View {
    @StateObject private var viewModel = OrderViewModel()
    @Binding var path: NavigationPath
    @State private var showToast = false
    @State private var toastMessage : String? = ""

    var body: some View {
        VStack {
        
            if viewModel.myOrderItems.isEmpty {
                Text(toastMessage ?? "")
            }
            
            else {
                List {
                    ForEach(viewModel.myOrderItems) { item in
                        OrderHistpryListWidget(
                           order: item
                        )
                        .onTapGesture {
                            path.append(HomeRoute.orderDetails(orderItemUiModel: item))
                        }
                    }
                }
            }
        }
        .navigationTitle("Order History")
        .onAppear {
            viewModel.getOrders()
        }
        .navigationTitle("My Orders")
        .overlay {
            if (viewModel.getMyOrderState.isLoading ) {
                LoaderOverlay(text: "Products Loading...")
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
        .errorAlert(errorMessage: toastMessage, isShow: $showToast)
        .onReceive(viewModel.$getMyOrderState) { state in
            switch state {
            case .loading:
                print("Show loader")

            case .success:
                print("Success")

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
        
    }
}
