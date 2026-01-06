//
//  ProfileViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 3/1/26.
//

import SwiftUI
import sharedKit

@MainActor
final class ProfileViewModel: ObservableObject {

    @Published var profile: ProfileResponse?
    @Published var isLoading = false
    @Published var errorMessage: String?

    private let service = ProfileService()


    func fetchProfile() {
        isLoading = true
        errorMessage = nil

        Task {
            do {
                profile = try await service.fetchProfile()
            } catch let error as APIError {
                errorMessage = error.localizedDescription

                if error == .unauthorized {
                    //UserSessionProvider.shared.userSession.clear()
                }
            } catch {
                errorMessage = "Unexpected error"
            }

            isLoading = false
        }
    }


}
