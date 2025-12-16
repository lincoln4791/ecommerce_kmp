import Foundation
import sharedKit

@MainActor
class AuthViewModel: ObservableObject {

    @Published var email: String = ""
    @Published var password: String = ""
    @Published var isLoading: Bool = false
    @Published var errorMessage: String = ""
    @Published var isLoggedIn: Bool = false

    private let controller = AuthController()

    func login() {
        errorMessage = ""
        isLoading = true

        Task {
            let result = await controller.login(
                email: email,
                password: password
            )

            isLoading = false

            switch result {

            case let success as ResponseSuccess<LoginResponse>:
                print("LOGIN DATA:", success.data)
                isLoggedIn = true

            case let error as ResponseError:
                errorMessage = error.message

            default:
                errorMessage = "Unknown error"
            }
        }
    }
}
