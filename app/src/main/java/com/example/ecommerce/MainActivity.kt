package com.example.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ecommerce.ui.theme.EcommerceTheme
import com.example.ecommerce.navigation.RootNavHost
import com.example.shared.data.keyValueStorage.UserSession
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val userSession: UserSession by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            EcommerceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    RootNavHost(navController, userSession)
                }
            }
        }
    }
}