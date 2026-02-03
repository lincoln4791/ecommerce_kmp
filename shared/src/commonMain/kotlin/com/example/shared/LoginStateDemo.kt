package com.example.shared

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class LoginStateDemo {
    suspend fun startChanging() {
        while (true){
            delay(3000) // 3 seconds
            //_count.value = Random.nextInt(1,100)
        }
    }
}