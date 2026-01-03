import SwiftUI
import sharedKit

struct OrderHistoryDetailsView: View {
    @StateObject private var viewModel = OrderViewModel()
    @Binding var path: NavigationPath
    let order: MyOrderDataUiModel
    
    @State private var showToast = false
    @State private var toastMessage = ""

    var body: some View {
        ScrollView {
                   VStack(spacing: 16) {

                       OrderSummaryCard(order: order)

                       OrderedItemsCard(orderedItems: order.items)

                       PaymentSummaryCard(order: order)
                   }
                   .padding()
               }
        .onAppear {
            viewModel.getOrders()
        }
        .navigationTitle("Order #\(order.id)")
        .navigationBarTitleDisplayMode(.inline)
        .overlay {
            if (viewModel.getMyOrderState.isLoading ) {
                ZStack {
                    Color.black.opacity(0.1).ignoresSafeArea()
                    ProgressView("Loading products...")
                        .padding(24)
                        .background(.ultraThinMaterial)
                        .cornerRadius(12)
                }
            }
        }
        .overlay(alignment: Alignment.bottom) {
            if showToast {
                ToastView(message: toastMessage)
                    .padding(Edge.Set.bottom, 40)
                    .transition(
                        .move(edge: Edge.bottom)
                            .combined(with: .opacity)
                    )
            }
        }
        .onReceive(viewModel.$getMyOrderState) { state in
            switch state {
            case .loading:
                print("Show loader")

            case .success:
                print("Success")

            case .error(let message):
                toastMessage = message.isEmpty ? "Something went wrong" : message
                showToast = true

                DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                    withAnimation {
                        showToast = false
                    }
                }

            case .idle:
                break
            }
        }
        
    }
}
