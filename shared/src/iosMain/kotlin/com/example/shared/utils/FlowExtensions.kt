package com.example.shared.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.watch(
    scope: CoroutineScope,
    block: (T) -> Unit
) {
    scope.launch {
        collect { block(it) }
    }
}
