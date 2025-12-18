//
//  HomeView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 16/12/25.
//


import SwiftUI

struct HomeView: View {
    
    @ObservedObject var vm = AuthViewModel()
    @Binding var path: NavigationPath

    var body: some View {
        VStack {
            Text("Home Screen 🎉")
                .font(.largeTitle)
            
            Button("Logout") {
                vm.logout()
                //vm.isLoggedIn = false
                path.append(AppRoute.login)
            }
        }
        .padding()
    }
}
