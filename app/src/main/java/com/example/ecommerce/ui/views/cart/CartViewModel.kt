package com.example.ecommerce.ui.views.cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.navigation.MainScreens
import com.example.ecommerce.ui.views.cart.model.CartDataUiModel
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartController: CartController
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState : StateFlow<CartUiState> = _uiState

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        getCart()
    }

    fun addToCart() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val response = cartController.addToCart(addToCartRequest = AddToCartRequest(productId = 3, quantity = 1))
                if(response is AddToCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("Login success"))
                        _uiEvent.send(UiEvent.NavigateTo(MainScreens.Home))
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
                _uiState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        }
    }

    fun cartBulkUpdate() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val cartBulkUpdateItem = CartBulkUpdateItem(productId = 1,4)
                val cartBulkUpdateItem2 = CartBulkUpdateItem(productId = 3,4)
                val cartBulkUpdateRequest = CartBulkUpdateRequest(arrayListOf(cartBulkUpdateItem,cartBulkUpdateItem2))
                val response = cartController.cartBulkUpdate(cartBulkUpdateRequest)
                if(response is CartBulkUpdateResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast(" success"))
                        _uiEvent.send(UiEvent.NavigateTo(MainScreens.Home))
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
                _uiState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        }
    }

    fun getCart() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }

            try {
                val response = cartController.getCart()
                if(response is GetCartResponseBase.Success){
                    if(response.data.data!=null){
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                cart = response.data.data!!.map { it -> CartDataUiModel.fromCartData(cartData = it) }.toList(),
                                error = null

                            )
                        }
                        _uiEvent.send(UiEvent.ShowToast("get cart success"))
                        //_uiEvent.send(UiEvent.NavigateTo(MainScreens.Home))
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
                _uiState.update {
                    it.copy(isLoading = true, error = null)
                }
            }
        }
    }

}
