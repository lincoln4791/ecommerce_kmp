//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 3/1/26.
//

import SwiftUI

@MainActor
final class ProfileViewModel: ObservableObject {

    @Published var profile: ProfileResponse?
    @Published var isLoading = false
    @Published var errorMessage: String?

    private let service = ProfileService()

    func loadProfile() {
        isLoading = true
        errorMessage = nil

        Task {
            do {
                //profile = try await service.fetchProfile()
                profile = try await service.updateProfile()
            } catch {
                errorMessage = error.localizedDescription
            }
            isLoading = false
        }
    }
}
