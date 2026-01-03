//
//  InfoRow.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 1/1/26.
//

import SwiftUI
struct InfoRow: View {
    let title: String
    let value: String

    var body: some View {
        HStack {
            Text(title)
                .foregroundColor(.secondary)
            Spacer()
            Text(value)
        }
    }
}
