import SwiftUI

struct ContentView: View {

    @StateObject private var vm = AuthViewModel()
    @State private var path = NavigationPath()

    var body: some View {
        NavigationStack(path: $path) {
            Group {
                if vm.isLoggedIn {
                    HomeView(vm: vm, path: $path)
                } else {
                    LoginView(vm: vm, path: $path)
                }
            }
            .navigationDestination(for: AppRoute.self) { route in
                switch route {
                case .home:
                    HomeView(vm: vm, path: $path)

                case .login:
                    LoginView(vm: vm, path: $path)

                //case .profile:
                    //ProfileView()

                //case .settings:
                    //SettingsView()
                default:
                        EmptyView()
                }
            }
        }
    }
}

#Preview {
    ContentView()
}

