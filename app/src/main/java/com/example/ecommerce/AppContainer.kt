package com.example.ecommerce

import android.content.Context
import com.example.shared.data.keyValueStorage.AndroidKeyValueStorage
import com.example.shared.data.keyValueStorage.JsonStorage
import com.example.shared.data.keyValueStorage.UserSession

// app/src/main/java/.../AppContainer.kt
class AppContainer(context: Context) {
    private val keyValueStorage = AndroidKeyValueStorage(context)
    private val jsonStorage = JsonStorage(keyValueStorage)
    val userSession = UserSession(jsonStorage)
}
