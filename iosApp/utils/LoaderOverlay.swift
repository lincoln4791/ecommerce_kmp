//
//  LoaderOverlay.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 2/1/26.
//

import SwiftUI
struct LoaderOverlay: View {
    let text: String

    var body: some View {
        ZStack {
            Color.black.opacity(0.15)
                .ignoresSafeArea()

            VStack(spacing: 12) {
                ProgressView()
                    .scaleEffect(1.2)

                Text(text)
                    .font(.subheadline)
                    .foregroundStyle(.primary)
            }
            .frame(width: 220)
            .padding()
            .background(.ultraThinMaterial)
            .cornerRadius(12)
        }
    }
}

