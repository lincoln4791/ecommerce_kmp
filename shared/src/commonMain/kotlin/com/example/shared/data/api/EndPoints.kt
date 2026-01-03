package com.example.shared.data.api

object EndPoints {

    //Auth
    const val LOGIN = "/auth/login"
    const val REFRESH = "/auth/refresh"
    const val REGISTRATION = "/auth/signup"

    //Products
    const val GET_PRODUCTS = "/products"


    //cart
    const val ADD_TO_CART = "/cart/add"
    const val BULK_UPDATE_CART = "/cart/bulkUpdate"
    const val GET_CART = "/cart/items"

    //Order
    const val PLACE_ORDER_FROM_CART = "/order/placeFromCart"
    const val GET_MY_ORDERS = "/order/orders"

}