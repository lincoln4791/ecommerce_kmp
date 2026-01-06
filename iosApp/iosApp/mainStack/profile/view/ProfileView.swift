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
    
    @State private var showAlert: Bool = false
    @State private var alertMessage: String? = ""

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
            viewModel.fetchProfile()
        }
        .onReceive(viewModel.$errorMessage) { message in
            if let message , !message.isEmpty {
                self.showAlert = true
                self.alertMessage = message
            }
        }
        .errorAlert(errorMessage: alertMessage, isShow: $showAlert)
    }
}

