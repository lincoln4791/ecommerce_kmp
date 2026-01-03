package com.example.shared// iosMain
import platform.Foundation.NSLog

actual fun log(message: String) {
    NSLog(message)
}
