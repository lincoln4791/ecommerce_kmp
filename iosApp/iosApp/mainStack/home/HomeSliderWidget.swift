//
//  AutoSliderView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
struct HomeSliderWidget: View {

    let images : [HomeSliderModel]

    @State private var index = 0
    let timer = Timer.publish(every: 3, on: .main, in: .common).autoconnect()

    var body: some View {
        TabView(selection: $index) {
            ForEach(images.indices, id: \.self) { i in
                Image(images[i].image)
                    .resizable()
                    .scaledToFill()
                    .frame(height: 200)
                    .tag(i)
            }
        }
        .tabViewStyle(.page)
        .frame(height: 200)
        .clipped()
        .clipShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        .onReceive(timer) { _ in
            withAnimation {
                index = (index + 1) % images.count
            }
        }
    }
}
