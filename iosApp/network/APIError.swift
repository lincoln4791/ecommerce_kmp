//
//  APIError.swift
//  iosApp
//
//  Created by Mahmudul Karim Lincoln on 3/1/26.
//


import Foundation
import sharedKit

enum APIError: LocalizedError {
    case invalidURL
    case invalidResponse
    case decodingError
    case unauthorized
}



