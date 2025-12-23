//
//  AuthStack.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI
struct AuthStack: View {

    @EnvironmentObject var authVM : AuthViewModel
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            RootView()
                .navigationDestination(for: AppRoute.self) { route in
                    switch route {
                    case .login:
                        LoginView(path: $path)
                            .navigationBarBackButtonHidden(true)

//                    case .home:
//                        HomeView(vm: authVM, path: $path)
//                            .navigationBarBackButtonHidden(true)
                    default: EmptyView()
                    }
                }
        }
        .onAppear {
            routeFromAuthState()
        }
        .onChange(of: authVM.isLoggedIn) { _, _ in
            routeFromAuthState()
        }
    }

    private func routeFromAuthState() {
        path.removeLast(path.count)

        if authVM.isLoggedIn {
            //path.append(AppRoute.home)
        } else {
            path.append(AppRoute.login)
        }
    }
}

struct RootView: View {
    var body: some View {
        Color.clear
    }
}

enum AuthRoute: Hashable {
    case login
    case register
}
