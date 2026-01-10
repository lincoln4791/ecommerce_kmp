package com.example.ecommerce.ui.views.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector

object FakeDataUtils {
    fun getRandomIconList() : List<ImageVector>{
        return listOf(
            Icons.Outlined.Home,
            Icons.Outlined.Star,
            Icons.Outlined.Person,
            Icons.Outlined.Settings,
            Icons.Outlined.Favorite,
            Icons.Outlined.Face,
            Icons.Outlined.Call,
            Icons.Outlined.CheckCircle,
            Icons.Outlined.Add,
            Icons.Outlined.AccountBox,
            Icons.Outlined.Create,
            Icons.Outlined.Delete,
            Icons.Outlined.DateRange,
            Icons.Outlined.Email,
            Icons.Outlined.Info,
            Icons.Outlined.ShoppingCart,
        )

    }
}