import Foundation
import sharedKit

final class APIClient {

    static let shared = APIClient()
    private init() {}

    private let session = URLSession.shared

    func request(
        url: URL,
        method: String = "GET",
        body: Data? = nil,
        requiresAuth: Bool = true
    ) async throws -> Data {

        var request = URLRequest(url: url)
        request.httpMethod = method
        request.httpBody = body
        request.setValue("application/json", forHTTPHeaderField: "Accept")

        if body != nil {
            request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        }

        if requiresAuth {
            guard let token = UserSessionProvider.shared.userSession.getAccessToken(),
                  !token.isEmpty else {
                throw APIError.unauthorized
            }
            request.setValue("Bearer \(token)", forHTTPHeaderField: "Authorization")
        }

        let (data, response) = try await session.data(for: request)

        guard let httpResponse = response as? HTTPURLResponse else {
            throw APIError.invalidResponse
        }

        #if DEBUG
        print("STATUS:", httpResponse.statusCode)
        print(String(data: data, encoding: .utf8) ?? "No body")
        #endif

        switch httpResponse.statusCode {
        case 200...299:
            return data

        case 401:
            throw APIError.unauthorized

        case 403:
            throw APIError.forbidden

        case 500...599:
            throw APIError.serverError(httpResponse.statusCode)

        default:
            throw APIError.unknown
        }
    }
}
