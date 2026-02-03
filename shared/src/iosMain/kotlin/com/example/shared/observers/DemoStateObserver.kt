package com.example.shared.observers

import com.example.shared.DemoState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DemoStateObserver(
    private val demoState: DemoState
) {
    fun observe(
        scope: CoroutineScope,
        onChange: (Int) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            demoState.count.collect { value ->
                onChange(value)
            }
        }
    }
}