package com.example.playground

fun main(){
    val a : String? = "Lincoln"
    val r =  a?.let {
        ""
        1
    }

    val result = run {
        val a = 10
        val b = 20
        a + b
    }
    print(result)

    val result2 = with(a){
        ""
        //1
    }

    val paint = User().apply {
        id = 1
        name=""
    }

}




