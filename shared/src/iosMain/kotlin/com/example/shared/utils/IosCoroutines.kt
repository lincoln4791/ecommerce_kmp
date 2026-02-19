package com.example.shared.utils

import kotlinx.coroutines.*

fun createMainScope(): CoroutineScope {
    return MainScope()
}