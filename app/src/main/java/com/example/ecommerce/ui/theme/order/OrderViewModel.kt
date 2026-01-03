package com.example.ecommerce.ui.theme.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.navigation.Screen
import com.example.ecommerce.utils.UiEvent
import com.example.shared.data.model.requests.cart.AddToCartRequest
import com.example.shared.domain.model.baseResultModels.Order.GetMyOrdersResponseBase
import com.example.shared.domain.model.baseResultModels.Order.PlaceOrderFromCartResponseBase
import com.example.shared.domain.model.baseResultModels.cart.AddToCartResponseBase
import com.example.shared.domain.model.toUiMessage
import com.example.shared.presentation.OrderController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderController: OrderController
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun orderFromCart() {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = orderController.placeOrderFromCart()
                if(response is PlaceOrderFromCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                    }

                }
                else if (response is PlaceOrderFromCartResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

    fun getMyOrders() {
        viewModelScope.launch {
            isLoading = true

            try {
                val response = orderController.getMyOrders()
                if(response is GetMyOrdersResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("Get My Orders success"))
                        _uiEvent.send(UiEvent.NavigateTo(Screen.Home))
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("Get My Orders error"))
                    }

                }
                else if (response is GetMyOrdersResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "failed")
                )
            } finally {
                isLoading = false
            }
        }
    }

}
