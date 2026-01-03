//
//  MainTabView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI
struct MainTabView: View {
    @EnvironmentObject  var authVM : AuthViewModel

    var body: some View {
        TabView {
            
            HomeStack()
                .tabItem { Label("Feed", systemImage: "house") }

            FeedStack()
                .tabItem { Label("Feed", systemImage: "newspaper.fill") }

            DeliveryStack()
                .tabItem { Label("Delivery", systemImage: "truck.box") }

//            SalesStack()
//                .tabItem { Label("Sales", systemImage: "chart.bar") }
//
//            ProfileStack()
//                .tabItem { Label("Profile", systemImage: "person") }
        }
    }
}
