package com.example.shared.data.keyValueStorage

import platform.Foundation.NSUserDefaults
import platform.Foundation.NSBundle

class IosKeyValueStorage : KeyValueStorage {

    private val defaults = NSUserDefaults.standardUserDefaults
    private val bundleId = NSBundle.mainBundle.bundleIdentifier!!

    override fun putString(key: String, value: String) {
        defaults.setObject(value, forKey = key)
    }

    override fun getString(key: String): String? {
        return defaults.stringForKey(key)
    }

    override fun remove(key: String) {
        defaults.removeObjectForKey(key)
    }

    override fun clear() {
        defaults.removePersistentDomainForName(bundleId)
    }
}
