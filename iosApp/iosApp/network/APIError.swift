//
//  APIError.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 3/1/26.
//


import Foundation
import sharedKit

enum APIError: LocalizedError , Equatable {
    case unauthorized
    case forbidden
    case serverError(Int)
    case invalidResponse
    case decodingError
    case unknown

    var errorDescription: String? {
        switch self {
        case .unauthorized:
            return "Session expired. Please login again."
        case .forbidden:
            return "You don’t have permission to access this."
        case .serverError(let code):
            return "Server error (\(code))"
        case .invalidResponse:
            return "Invalid server response"
        case .decodingError:
            return "Failed to parse response"
        case .unknown:
            return "Something went wrong"
        }
    }
}



