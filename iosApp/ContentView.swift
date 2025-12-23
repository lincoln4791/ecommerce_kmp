import SwiftUI

struct ContentView: View {
    
    @EnvironmentObject  var vm : AuthViewModel
    var body: some View {
        Group {
            if vm.isLoggedIn {
                MainTabView()
            } else {
                AuthStack()
            }
        }
    }

}



