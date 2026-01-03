//
//  LoginView.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 16/12/25.
//


import SwiftUI

struct LoginView: View {

    @EnvironmentObject var authVM: AuthViewModel
    @Binding var path: NavigationPath


    var body: some View {
        GeometryReader { geo in
            VStack(spacing: 16) {

                // 1/3 height — image at bottom
                VStack {
                    Spacer()

                    Image("shop")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 120, height: 120)
                }
                .frame(height: geo.size.height / 3)

                // 2/3 height — login form
                VStack {

                    Text("Login")
                        .font(.largeTitle)

                    TextField("Email", text: $authVM.email)
                        .keyboardType(.emailAddress)
                        .autocapitalization(.none)
                        .padding()
                        .background(Color(.systemGray6))
                        .cornerRadius(10)

                    SecureField("Password", text: $authVM.password)
                        .padding()
                        .background(Color(.systemGray6))
                        .cornerRadius(10)

                    Button {
                        if authVM.validateLoginRequest() {
                            authVM.login()
                            //path.append(AppRoute.home)
                        }
                    } label: {
                        Text("Login")
                            .frame(maxWidth: .infinity)
                    }
                    .buttonStyle(.borderedProminent)
                    .controlSize(.large)

                    Spacer()
                }
                .frame(height: geo.size.height * 2 / 3)
            }
            .padding()
        }
    }




    
    
}
