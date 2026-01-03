//
//  HomeView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 16/12/25.
//


import SwiftUI

struct HomeView: View {
    
    @EnvironmentObject var authVM : AuthViewModel
    @StateObject var homeVM = HomeViewModel()
    @Binding var path: NavigationPath
    
    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    
    var body: some View {
        VStack() {
            HStack(){
                Text("Home Screen 🎉")
                    .font(.title)
                Spacer()
            }
            Spacer().frame(height: 30)
            
            LazyVGrid(columns: columns) {
                ForEach(homeVM.mainCardItems) { item in
                    HomeCardView(title: "\((item.title))", image: "\((item.image))").onTapGesture {
                        path.append(item.route)
                    }
                }
            }
            Spacer().frame(height:20)
            HomeSliderWidget(images:  homeVM.sliderItems)
            Spacer()
            
            Button("Logout") {
                authVM.logout()
                authVM.isLoggedIn = false
                
            }
        }
        .padding()
    }
}
