//
//  iosAppApp.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 15/12/25.
//

import SwiftUI
import sharedKit


@main
struct iosAppApp: App {
    init() {
        // 👇 This is where you start Koin
        InitKoinIosKt.doInitKoinIos();
        print("🔥 Koin iOS initialized")
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
