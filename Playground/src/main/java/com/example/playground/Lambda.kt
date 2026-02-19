package com.example.playground

fun main(){
    print("add ->  ${add(1,2)}\n")
    print("add2 -> ${add2(2,3)}")
    val a =   calculate(3,4){a,b->
        a+b
        //print("")
    }

    listOf(1,2,3).forEach { _ ->

    }
}

fun add(a:Int,b:Int) : Int{
    return a+b
}
val add2 = {a:Int,b:Int->a+b}

fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int) {
    val result = operation(a, b)
    println(result)
}

fun calculate2(a: Int, b: Int, operation: (Int, Int) -> Unit) {
    val result = operation(a, b)
    println(result)
}

