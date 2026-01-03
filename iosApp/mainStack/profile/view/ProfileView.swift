//
//  LoginView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 16/12/25.
//


import SwiftUI
struct ProfileView: View {

    @Binding var path: NavigationPath
    @StateObject private var viewModel = ProfileViewModel()

    var body: some View {
        ZStack {

            if let profile = viewModel.profile {
                VStack(spacing: 12) {
                    Text((profile.data ?? ProfileData(name: "", phone:"", email: "", role: "")).name)
                        .font(.title2)

                    Text(profile.data!.email)
                    Text(profile.data!.phone)

                    Text(profile.data!.role)
                        .font(.caption)
                        .foregroundColor(.gray)
                }
            }

            if viewModel.isLoading {
                LoaderOverlay(text: "Loading profile...")
            }
        }
        .navigationTitle("Profile")
        .task {
            viewModel.loadProfile()
        }
    }
}

