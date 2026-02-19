package com.example.ecommerce.ui.theme.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.navigation.Screen
import com.example.ecommerce.utils.UiEvent
import com.example.shared.domain.model.toUiMessage
import com.example.shared.data.keyValueStorage.UserSession
import com.example.shared.data.model.requests.RefreshTokenRequest
import com.example.shared.domain.model.baseResultModels.auth.LoginResponseBase
import com.example.shared.domain.model.baseResultModels.auth.RefreshTokenResponseBase
import com.example.shared.presentation.AuthController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authController: AuthController,
    private val userSession: UserSession
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var message by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun checkLoginAndNavigate() {
        if (isLoggedIn()) {
            viewModelScope.launch {
                _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
            }
        }
    }

    fun isLoggedIn() : Boolean{
        return userSession.isLoggedIn()
    }

    @Deprecated(
        message = "Use loginV2()",
        replaceWith = ReplaceWith("loginV2(email, password)")
    )
    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = authController.login(email, password)
                if(response is LoginResponseBase.Success){
                    if(response.data.data!=null){
                        userSession.saveUser(response.data.data!!)
                        _uiEvent.send(UiEvent.ShowToast("Login success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is LoginResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "Login failed")
                )
            } finally {
                isLoading = false
            }
        }
    }


    fun refreshToken(refreshTokenRequest: RefreshTokenRequest) {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = authController.refreshToken(refreshTokenRequest)
                if(response is RefreshTokenResponseBase.Success){
                    if(response.data.data!=null){
                        val user =  userSession.getUser()
                        user?.token=response.data.data!!.access_token
                        userSession.saveUser(user!!)
                        _uiEvent.send(UiEvent.ShowToast("Login success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is RefreshTokenResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "Login failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

    fun logout(){
        userSession.logout()
    }
}
