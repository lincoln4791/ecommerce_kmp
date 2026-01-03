package com.example.shared.data.keyValueStorage

interface KeyValueStorage {
    fun putString(key: String, value: String)
    fun getString(key: String): String?
    fun remove(key: String)
    fun clear()
}
