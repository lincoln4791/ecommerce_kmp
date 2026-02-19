package com.example.ecommerce.ui.views.order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.navigation.MainScreens
import com.example.ecommerce.ui.views.cart.CartUiState
import com.example.ecommerce.ui.views.cart.model.CartDataUiModel
import com.example.ecommerce.ui.views.order.model.OrderDataUiModel
import com.example.ecommerce.utils.UiEvent
import com.example.shared.domain.model.baseResultModels.Order.GetMyOrdersResponseBase
import com.example.shared.domain.model.baseResultModels.Order.PlaceOrderFromCartResponseBase
import com.example.shared.domain.model.toUiMessage
import com.example.shared.presentation.OrderController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderViewModel(
    private val orderController: OrderController
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState : StateFlow<OrderUiState> = _uiState

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getMyOrders()
    }

    fun orderFromCart() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true,error=null)
            }

            try {
                val response = orderController.placeOrderFromCart()
                if(response is PlaceOrderFromCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("success"))
                        _uiEvent.send(UiEvent.NavigateTo(MainScreens.Home))
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
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun getMyOrders() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val response = orderController.getMyOrders()
                if(response is GetMyOrdersResponseBase.Success){
                    if(response.data.data!=null){
                        _uiState.update {
                            it.copy(isLoading = false,
                                order = response.data.data!!.map { it-> OrderDataUiModel.fromOrderData(it) }.toList(),
                                error = null)
                        }
                        _uiEvent.send(UiEvent.ShowToast("Get My Orders success"))
                        _uiEvent.send(UiEvent.NavigateTo(MainScreens.Home))
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
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

}
