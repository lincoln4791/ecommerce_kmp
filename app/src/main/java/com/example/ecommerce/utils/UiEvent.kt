package com.example.ecommerce.utils

import com.example.ecommerce.navigation.MainScreens

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()

    data class NavigateTo(val screen: MainScreens) : UiEvent()
}