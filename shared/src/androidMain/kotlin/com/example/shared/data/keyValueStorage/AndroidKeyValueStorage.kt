package com.example.shared.data.keyValueStorage

import android.content.Context

class AndroidKeyValueStorage(
    context: Context
) : KeyValueStorage {

    private val prefs =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}
