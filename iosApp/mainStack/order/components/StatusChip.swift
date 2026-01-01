//
//  StatusChip.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//


import SwiftUI
struct StatusChip: View {
    let text: String

    var body: some View {
        Text(text)
            .font(.caption)
            .padding(.horizontal, 10)
            .padding(.vertical, 4)
            .background(Color.orange.opacity(0.15))
            .foregroundColor(.orange)
            .cornerRadius(8)
    }
}
