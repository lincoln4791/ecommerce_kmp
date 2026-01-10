package com.example.ecommerce.ui.views.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.utils.UiEvent
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.presentation.AuthController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val authController: AuthController,
    private val userSession: UserSession
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun logout(){
        viewModelScope.launch {
            userSession.logout()
            _uiEvent.send(UiEvent.ShowToast("Logout Success"))
            //_uiEvent.send(UiEvent.NavigateTo(AuthScreens.Login))
        }

    }
}
