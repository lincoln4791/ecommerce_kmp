//
//  PaymentSummaryCard.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import SwiftUI
struct PaymentSummaryCard: View {

    let order: MyOrderDataUiModel

    var body: some View {
        VStack(spacing: 8) {
            SummaryRow(title: "Total Amount", value: order.totalAmount)
        }
        .cardStyle()
    }
}
