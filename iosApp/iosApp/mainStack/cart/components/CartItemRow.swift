//
//  CartItemRow.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 28/12/25.
//

import SwiftUI
import sharedKit

struct CartItemRow: View {

    let item: CartDataUiModel
    let onUpdate: (Int) -> Void
    let onDelete: () -> Void

    @State private var quantity: Int

    init(item: CartDataUiModel,
         onUpdate: @escaping (Int) -> Void,
         onDelete: @escaping () -> Void) {
        self.item = item
        self.onUpdate = onUpdate
        self.onDelete = onDelete
        _quantity = State(initialValue: Int(item.quantity))
    }

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(item.productName)
                    .font(.headline)

                Text("৳ \(item.price)")
                    .foregroundColor(.gray)
            }

            Spacer()

            HStack {
                Button("-") {
                    if quantity > 1 {
                        quantity -= 1
                        onUpdate(quantity)
                    }
                }.buttonStyle(.plain)

                Text("\(quantity)")
                    .frame(width: 30)

                Button("+") {
                    quantity += 1
                    onUpdate(quantity)
                }.buttonStyle(.plain)
            }

            Button {
                onDelete()
            } label: {
                Image(systemName: "trash")
                    .foregroundColor(.red)
            }.buttonStyle(.plain)
        }
    }
}
