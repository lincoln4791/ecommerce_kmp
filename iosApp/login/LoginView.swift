//
//  LoginView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 16/12/25.
//


import SwiftUI

struct LoginView: View {

    @ObservedObject var vm: AuthViewModel

    var body: some View {
        VStack(spacing: 16) {

            Text("Login")
                .font(.largeTitle)

            TextField("Email", text: $vm.email)
                .textFieldStyle(.roundedBorder)
                .autocapitalization(.none)

            SecureField("Password", text: $vm.password)
                .textFieldStyle(.roundedBorder)

            if vm.isLoading {
                ProgressView()
            }

            Button("Login") {
                vm.login()
            }
            .disabled(vm.isLoading)

            if !vm.errorMessage.isEmpty {
                Text(vm.errorMessage)
                    .foregroundColor(.red)
            }
        }
        .padding()
    }
}
