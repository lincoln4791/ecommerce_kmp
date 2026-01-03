package com.example.ecommerce.ui.theme.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.navigation.Screen
import com.example.ecommerce.utils.UiEvent
import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.data.model.requests.cart.CartBulkUpdateItem
import com.example.shared.data.model.requests.cart.CartBulkUpdateRequest
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.baseResultModels.cart.CartBulkUpdateResponseBase
import com.example.shared.domain.model.baseResultModels.cart.GetCartResponseBase
import com.example.shared.domain.model.toUiMessage
import com.example.shared.presentation.CartController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartController: CartController
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun addToCart() {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = cartController.addToCart(addToCartRequest = AddToCartRequest(productId = 3, quantity = 1))
                if(response is AddToCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("Login success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is AddToCartResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "Login failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

    fun cartBulkUpdate() {
        viewModelScope.launch {
            isLoading = true

            try {
                val cartBulkUpdateItem = CartBulkUpdateItem(productId = 1,4)
                val cartBulkUpdateItem2 = CartBulkUpdateItem(productId = 3,4)
                val cartBulkUpdateRequest = CartBulkUpdateRequest(arrayListOf(cartBulkUpdateItem,cartBulkUpdateItem2))
                val response = cartController.cartBulkUpdate(cartBulkUpdateRequest)
                if(response is CartBulkUpdateResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast(" success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is CartBulkUpdateResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: " failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

    fun getCart() {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = cartController.getCart()
                if(response is GetCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("get cart success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is GetCartResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "Login failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

}
