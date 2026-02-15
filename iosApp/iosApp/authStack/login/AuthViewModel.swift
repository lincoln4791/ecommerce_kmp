import Foundation
import sharedKit


@MainActor
class AuthViewModel: ObservableObject {
    @Published var flag = 1
    @Published var email = ""
    @Published var emailError = ""
    @Published var password = ""
    @Published var isLoading = false
    @Published var errorMessage = ""
    @Published var isLoggedIn = false
    


    private let userSession: UserSession = UserSessionProvider.shared.userSession

    private let scope = IosCoroutinesKt.createMainScope()
    private let demoState = DemoState()

    private let controller = ControllerProvider.shared.getAuthController()


    init(){
        self.isLoggedIn = userSession.isLoggedIn()
        
        let loginObserver = UserLoginStateObserver(userSession: userSession)
        loginObserver.observeLoginState(scope : scope) { [weak self] (loggedIn : KotlinBoolean)  in
            guard let self = self else { return }
            print("logi observed")
            if(loggedIn.boolValue==false){
                self.isLoggedIn = false
            }
            else{
                self.isLoggedIn = true
            }
        }
        
#if DEBUG
        email = "test@mail.com"
        password = "12345678"
#endif
    }
    

    
    func getUser()-> LoginData?{
        let userData = userSession.getUser()
        print("Existing User Data",userData)
        return userData;
    }
    
    func logout(){
        userSession.logout()
    }

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
                case let success as LoginResponseBase.Success:
                    self.userSession.saveUser(userInfo: success.data.data!)
                    self.userSession.loginState()

                case let error as LoginResponseBase.Error:
                    self.errorMessage = error.error.toUiMessage()

                default:
                    self.errorMessage = "Unknown error"
                }
            }
        }
    }
    
    func validateLoginRequest() -> Bool {
        var isValid = true
        
        if email.isEmpty {
            errorMessage = "Email is required."
            isValid = false
        }
        
        if !isValidEmail() {
            errorMessage = "Invalid email format."
            isValid = false
        }
        if password.isEmpty {
            errorMessage = "Password is required."
            isValid = false
        }
        
        return isValid
        
    }
    
    func isValidEmail() -> Bool {
        let result = EmailValidator.shared.validate(email :email)
        switch result {
        case let error as ValidationResultBase.Error:
            return false
            
        default:
            return true
        }
    }
    
}

