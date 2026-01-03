package com.example.ecommerce.ui.theme.login

import androidx.lifecycle.ViewModel
import com.example.ecommerce.AppContainer

class AuthViewModel(
    appContainer: AppContainer
) : ViewModel() {

    private val userSession = appContainer.userSession

    fun checkLogin() {
        val loggedIn = userSession.isLoggedIn()
    }
}
