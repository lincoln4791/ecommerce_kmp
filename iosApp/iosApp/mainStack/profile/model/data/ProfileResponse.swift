//
//  ProfileResponse.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 3/1/26.
//


import Foundation

struct ProfileResponse: Decodable {
    let statusCode: Int
    let message: String
    let errors: String?
    let data: ProfileData?

    enum CodingKeys: String, CodingKey {
        case statusCode = "status_code"
        case message
        case errors
        case data
    }
}
