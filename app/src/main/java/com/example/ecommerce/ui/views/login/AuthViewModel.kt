package com.example.ecommerce.ui.views.login

import androidx.lifecycle.ViewModel
import com.example.ecommerce.AppContainer

class AuthViewModel(
    appContainer: AppContainer
) : ViewModel() {

    private val userSession = appContainer.userSession

}
