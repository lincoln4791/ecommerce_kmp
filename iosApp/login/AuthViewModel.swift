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

        controller.login(email: email, password: password) { result, error in
            DispatchQueue.main.async {
                self.isLoading = false

                if let error = error {
                    self.errorMessage = error.localizedDescription
                    return
                }

                guard let result = result else {
                    self.errorMessage = "Unknown error"
                    return
                }

                switch result {

                case let success as LoginResultBase.Success:
                    print("LOGIN DATA:", success.data)
                    self.isLoggedIn = true

                case let error as LoginResultBase.Error:
                    self.errorMessage = error.message

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
}
