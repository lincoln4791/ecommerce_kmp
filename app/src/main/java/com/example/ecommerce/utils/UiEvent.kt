package com.example.ecommerce.utils

import com.example.ecommerce.navigation.Screen

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()

    data class NavigateTo(val screen: Screen) : UiEvent()
}