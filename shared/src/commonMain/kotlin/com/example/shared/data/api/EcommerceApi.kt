package com.example.shared.data.api

import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.data.model.responses.cart.AddToCartResponse
import com.example.shared.data.model.responses.cart.CartBulkUpdateResponse
import com.example.shared.data.model.responses.cart.GetCartResponse
import com.example.shared.data.model.responses.order.GetMyOrdersResponse
import com.example.shared.data.model.responses.order.PlaceOrderFromCartResponse
import com.example.shared.data.model.responses.product.ProductsResponse

class EcommerceApi(private val apiClient: ApiClient) {


    //Products
    suspend fun getProducts(): ProductsResponse {
        return apiClient.get(
            url = baseUrl+ EndPoints.GET_PRODUCTS
        )
    }


    //cart
    suspend fun addToCart(addToCartRequest: AddToCartRequest): AddToCartResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.ADD_TO_CART,
            body = addToCartRequest
        )
    }

    suspend fun cartBulkUpdate(cartBulkUpdateRequest: CartBulkUpdateRequest): CartBulkUpdateResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.BULK_UPDATE_CART,
            body = cartBulkUpdateRequest
        )
    }
    suspend fun getCart(): GetCartResponse {
        return apiClient.get(
            url = baseUrl+ EndPoints.GET_CART
        )
    }

    //Order
    suspend fun placeOrderFromCart(): PlaceOrderFromCartResponse {
        return apiClient.post(
            url = baseUrl+ EndPoints.PLACE_ORDER_FROM_CART,
            body = null
        )
    }

    suspend fun getMyOrders(): GetMyOrdersResponse {
        return apiClient.get(
            url = baseUrl+ EndPoints.GET_MY_ORDERS
        )
    }


    companion object{
        const val baseUrl = "https://namely-driving-filly.ngrok-free.app"
    }
}
