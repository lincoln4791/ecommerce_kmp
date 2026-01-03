//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 23/12/25.
//

import SwiftUI
class HomeViewModel: ObservableObject {

    let mainCardItems: [HomeMainCardModel] = [
        HomeMainCardModel(id: 1, title: "Market", image: "cart.fill.badge.plus",route:.allProducts),
        HomeMainCardModel(id: 2, title: "My Cart", image: "cart.fill",route:.cart),
        HomeMainCardModel(id: 3, title: "My Orders", image: "purchased.circle.fill",route:.orderHiostory),
        HomeMainCardModel(id: 4, title: "Profile", image: "person.fill",route:.profile),
        HomeMainCardModel(id: 5, title: "Help", image: "questionmark.circle",route:.allProducts),
        HomeMainCardModel(id: 6, title: "Logout", image: "power",route:.allProducts)
    ]
    
    let sliderItems: [HomeSliderModel] = [
        HomeSliderModel(id: 1, image: "1"),
        HomeSliderModel(id: 2, image: "2"),
        HomeSliderModel(id: 3, image: "3"),
    ]
    
}
