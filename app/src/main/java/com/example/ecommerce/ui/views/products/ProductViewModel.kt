package com.example.ecommerce.ui.views.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.utils.UiEvent
import com.example.shared.data.model.responses.product.ProductsDataItem
import com.example.shared.domain.model.AppError
import com.example.shared.domain.model.baseResultModels.product.GetProductsResponseBase
import com.example.shared.domain.model.toUiMessage
import com.example.shared.presentation.ProductController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
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
    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onCategorySelected(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun onBrandSelected(brand: String?) {
        _uiState.update { it.copy(selectedBrand = brand) }
    }

    // -------------------------
    // Derived State (Filters)
    // -------------------------
    val filteredProducts: StateFlow<List<ProductsDataItem>> =
        combine(
            uiState.map { it.products },
            uiState.map { it.searchQuery },
            uiState.map { it.selectedCategory },
            uiState.map { it.selectedBrand }
        ) { products, search, category, brand ->

            products.filter { product ->

                val matchSearch =
                    search.isBlank() ||
                            product.name.contains(search, true)

                val matchCategory =
                    category == null ||
                            product.category.name == category

                val matchBrand =
                    brand == null ||
                            product.model.brand.name == brand

                matchSearch && matchCategory && matchBrand
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    val categories: StateFlow<List<String>> =
        uiState.map {
            it.products.map { p -> p.category.name }.distinct()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val brands: StateFlow<List<String>> =
        uiState.map {
            it.products.map { p -> p.model.brand.name }.distinct()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


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
