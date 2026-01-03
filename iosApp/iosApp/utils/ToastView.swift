//
//  ToastView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 31/12/25.
//

import SwiftUI

struct ToastView: View {
    let message: String

    var body: some View {
        Text(message)
            .foregroundColor(.white)
            .padding(.horizontal, 16)
            .padding(.vertical, 10)
            .background(Color.black.opacity(0.85))
            .cornerRadius(8)
            .shadow(radius: 4)
    }
}
