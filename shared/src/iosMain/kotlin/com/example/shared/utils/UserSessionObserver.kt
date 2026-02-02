package com.example.shared.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import com.example.shared.data.keyValueStorage.UserSession

class UserSessionObserver(
    private val userSession: UserSession
) {
    fun observeLoginState(
        scope: CoroutineScope,
        onChange: (Boolean) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            userSession.isLoggedInState.collect { value ->
                onChange(value)
            }
        }
    }
}
