package com.example.stackoverflow.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.stackoverflow.navigation.Navigation
import com.example.stackoverflow.ui.theme.StackOverflowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StackOverflowTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}
