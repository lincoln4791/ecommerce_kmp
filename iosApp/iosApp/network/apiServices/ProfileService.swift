//
//  ProfileService.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 4/1/26.
//

import Foundation
import SwiftUI
import sharedKit
final class ProfileService {

    func fetchProfile() async throws -> ProfileResponse {
        let url = URL(string: "\(ApiEndpoints.baseURL)/profile")!

        let data = try await APIClient.shared.request(
            url: url,
            requiresAuth: true
        )

        return try JSONDecoder().decode(ProfileResponse.self, from: data)
    }
    

    
    //func updateProfile(_ requestBody: UpdateProfileRequest) async throws -> ProfileResponse {
    func updateProfile() async throws -> ProfileResponse {
        let requestBody = UpdateProfileRequest(name: "Mahmudul", phone:"01670878110")
        let url = URL(string: "\(ApiEndpoints.baseURL)/profile/edit")!
        let body = try JSONEncoder().encode(requestBody)

        let data = try await APIClient.shared.request(
            url: url,
            method: "POST",
            body: body
        )

        return try JSONDecoder().decode(ProfileResponse.self, from: data)
    }
    
}

