package com.example.playground

fun main() {
test()
}

inline fun check(crossinline block: () -> Unit) {
    block()
}

fun test() {
    check {
        return@check  // returns from test()
    }
    println("Won't be printed")
}