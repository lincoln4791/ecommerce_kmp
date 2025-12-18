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
                .keyboardType(.emailAddress)
                .autocapitalization(.none)
                .onChange(of: vm.email) { _, _ in
                    let isValid = vm.isValidEmail()
                    if(!isValid){
                        vm.emailError = "Invalid email format"
                    }
                    else{
                        vm.emailError = ""
                    }
                }
            if !vm.emailError.isEmpty {
                Text(vm.emailError)
                    .font(.caption)
                    .foregroundColor(.red)
            }

            SecureField("Password", text: $vm.password)
                .textFieldStyle(.roundedBorder)

            if vm.isLoading {
                ProgressView()
            }

            Button("Login") {
                let isValid = vm.validateLoginRequest()
                if(isValid){
                    vm.login()
                }
                
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
