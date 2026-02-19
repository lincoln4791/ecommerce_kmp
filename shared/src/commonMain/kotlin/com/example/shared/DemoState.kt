package com.example.shared

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class DemoState {
    private val _count = MutableStateFlow(1)
    val count: StateFlow<Int> = _count

    suspend fun startTimer() {
        while (true){
            delay(3000) // 3 seconds
            _count.value = Random.nextInt(1,100)
        }
    }
}