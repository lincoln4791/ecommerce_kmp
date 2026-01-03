//
//  SummaryRow.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import SwiftUI
struct SummaryRow: View {
    let title: String
    let value: Double

    var body: some View {
        HStack {
            Text(title)
            Spacer()
            Text("৳ \(value, specifier: "%.2f")")
                .fontWeight(.bold)
        }
    }
}
