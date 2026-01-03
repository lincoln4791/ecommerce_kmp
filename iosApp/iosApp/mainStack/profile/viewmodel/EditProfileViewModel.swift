//
//  EditProfileViewModel.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 4/1/26.
//

import SwiftUI
import sharedKit

@MainActor
final class EditProfileViewModel: ObservableObject {

    @Published var name: String
    @Published var phone: String

    @Published var isLoading = false
    @Published var errorMessage: String?

    private let service = ProfileService()

    init(profile: ProfileResponse) {
        self.name = profile.data?.name ?? ""
        self.phone = profile.data?.phone ?? ""
    }

    func saveProfile(onSuccess: @escaping () -> Void) {
        isLoading = true
        errorMessage = nil

//        Task {
//            do {
//                let request = UpdateProfileRequest(
//                    name: name,
//                    phone: phone
//                )
//
//                _ = try await service.updateProfile(requestBody: request)
//                isLoading = false
//                onSuccess()
//            } catch {
//                errorMessage = error.localizedDescription
//                isLoading = false
//            }
//        }
    }
}
