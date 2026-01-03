package com.example.ecommerce.ui.theme.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.utils.UiEvent
import com.example.shared.domain.model.AppError
import com.example.shared.domain.model.baseResultModels.product.GetProductsResponseBase
import com.example.shared.domain.model.toUiMessage
import com.example.shared.presentation.ProductController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productController: ProductController,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductsUiState())
    val uiState: StateFlow<ProductsUiState> = _uiState

    private val _uiEvent = Channel<UiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()


    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true, error = null)
            }
            try {
                val response = productController.getProducts()
                if(response is GetProductsResponseBase.Success){
                    if(response.data.data!=null){
                        _uiEvent.send(UiEvent.ShowToast("Products Found"))
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                products = response.data.data!!.items,
                                error = null
                            )
                        }
                    }
                    else{
                        _uiEvent.send(UiEvent.ShowToast("error"))
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = AppError.Unknown("Error")
                            )
                        }
                    }

                }
                else if (response is GetProductsResponseBase.Error) {
                    _uiEvent.send(
                        UiEvent.ShowToast(response.error.toUiMessage())
                    )
                }

            } catch (e: Throwable) {
                _uiEvent.send(
                    UiEvent.ShowToast(e.message ?: "Failed")
                )
            } finally {

            }
        }
    }

}
