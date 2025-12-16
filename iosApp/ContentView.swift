import SwiftUI

struct ContentView: View {

    @StateObject private var vm = AuthViewModel()

    var body: some View {
        Group {
            if vm.isLoggedIn {
                HomeView()
            } else {
                LoginView(vm: vm)
            }
        }
    }
}

#Preview {
    ContentView()
}
