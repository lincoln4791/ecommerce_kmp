package com.example.shared.data.keyValueStorage

import com.example.shared.data.model.responses.auth.LoginData
import kotlinx.coroutines.flow.MutableStateFlow

class UserSession(
    private val jsonStorage: JsonStorage
) {

    fun saveUser(userInfo: LoginData) {
        jsonStorage.save(
            StorageKeys.USER_INFO,
            userInfo,
            LoginData.serializer()
        )
        saveAccessToken(userInfo.token)
    }

    fun getUser(): LoginData? {
        return jsonStorage.get(
            StorageKeys.USER_INFO,
            LoginData.serializer()
        )
    }

    fun saveAccessToken(token:String){
        return jsonStorage.saveString(
            key = StorageKeys.TOKEN,
            value = token
        )
    }
    fun getAccessToken(): String?{
        return jsonStorage.getString(
            key = StorageKeys.TOKEN
        )
    }


    fun isLoggedIn(): Boolean {
        return getUser() != null
    }
    val isLoggedInState = MutableStateFlow(isLoggedIn())

    fun logout() {
        jsonStorage.remove(StorageKeys.USER_INFO)
        jsonStorage.remove(StorageKeys.TOKEN)
        logoutState()
        //jsonStorage.clear()
    }

    fun loginState() {
        isLoggedInState.value = true
    }
    fun logoutState() {
        isLoggedInState.value = false
    }
}
