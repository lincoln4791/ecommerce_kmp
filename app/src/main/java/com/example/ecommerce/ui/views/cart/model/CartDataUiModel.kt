package com.example.ecommerce.ui.views.cart.model
import com.example.ecommerce.ui.views.products.model.ProductUiModel
import com.example.shared.data.model.responses.cart.CartData
import com.example.shared.data.model.responses.product.ProductsDataItem

data class CartDataUiModel(
    val id: Int,
    val product: ProductUiModel,
    val quantity: Int,
    val userId: Int
){
    companion object{
        fun fromCartData(cartData: CartData) : CartDataUiModel{
            return CartDataUiModel(
                id = cartData.id,
                product = ProductUiModel.fromProductDataItem(cartData.product),
                quantity = cartData.quantity,
                userId = cartData.userId

            )
        }
    }
}