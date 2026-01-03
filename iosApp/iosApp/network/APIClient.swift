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

        print("STATUS:", httpResponse.statusCode)
        print(String(data: data, encoding: .utf8) ?? "")

        if httpResponse.statusCode == 401 {
            throw APIError.unauthorized
        }

        guard 200..<300 ~= httpResponse.statusCode else {
            throw APIError.invalidResponse
        }

        return data
    }
}
