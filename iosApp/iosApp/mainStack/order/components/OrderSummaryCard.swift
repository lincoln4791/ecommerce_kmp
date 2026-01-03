//
//  OrderSummaryCard.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import SwiftUI

struct OrderSummaryCard: View {

    let order: MyOrderDataUiModel

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {

            StatusChip(text: order.deliveryStatus)

            InfoRow(title: "Payment", value: order.paymentMethod)
            InfoRow(title: "Items", value: "\(order.items.count)")
        }
        .cardStyle()
    }
}
