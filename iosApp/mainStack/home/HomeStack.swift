//
//  FeedStack.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI
struct HomeStack: View {
    @EnvironmentObject var authVM : AuthViewModel
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            HomeView(path: $path)
        }
    }
}

enum HomeRoute: Hashable {

}
