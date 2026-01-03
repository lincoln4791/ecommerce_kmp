package com.example.shared.data.keyValueStorage

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

class JsonStorage(
    private val storage: KeyValueStorage
) {

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    fun <T> save(key: String, data: T, serializer: kotlinx.serialization.KSerializer<T>) {
        val jsonString = json.encodeToString(serializer, data)
        storage.putString(key, jsonString)
    }

    fun <T> get(key: String, serializer: kotlinx.serialization.KSerializer<T>): T? {
        val jsonString = storage.getString(key) ?: return null
        return json.decodeFromString(serializer, jsonString)
    }

    fun saveString(
        key: String,
        value: String
    ){
        storage.putString(key, value)
    }

    fun getString(
        key: String
    ): String?{
        return storage.getString(key)
    }

    fun remove(key: String) {
        storage.remove(key)
    }

    fun clear() {
        storage.clear()
    }
}
