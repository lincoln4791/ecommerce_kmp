//
//  FeedStack.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 20/12/25.
//

import SwiftUI
struct FeedStack: View {

    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            FeedView(path: $path)
        }
    }
}

enum FeedRoute: Hashable {
    case post(String)
    case comments(String)
}
