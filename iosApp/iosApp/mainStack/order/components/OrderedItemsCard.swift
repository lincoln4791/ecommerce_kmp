

import SwiftUI
struct OrderedItemsCard: View {

    let orderedItems: [MyOrderItemUiModel]

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {

            Text("Ordered Items")
                .font(.headline)

            ForEach(orderedItems) { item in
                HStack{
                    Image(systemName:FakeDataUtils.productIcons.randomElement() ?? "iphone" )
                        .resizable()
                        .scaledToFit()
                        .frame(height: 50)
                        .frame(width: 50)
                        .foregroundColor(.pink)
                    VStack(spacing: 8) {
                        HStack {
                            VStack(alignment: .leading) {
                                Text(item.product.name)
                                    .font(.headline)

                                Text(item.product.model.brand.name)
                                    .font(.caption)
                                    .foregroundColor(.secondary)
                            }

                            Spacer()

                            Text("৳ \(item.price, specifier: "%.2f")")
                                .fontWeight(.semibold)
                        }

                        HStack {
                            Text("Qty: \(item.quantity)")
                                .foregroundColor(.secondary)

                            Spacer()

                            Text(
                                "Subtotal: ৳ \(Double(item.quantity) * item.price, specifier: "%.2f")"
                            )
                            .font(.subheadline)
                        }

                        Divider()
                    }
                }
              
            }
            

        }
        .cardStyle()
    }
}
