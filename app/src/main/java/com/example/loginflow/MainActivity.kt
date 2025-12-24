package com.example.loginflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginflow.presentation.home.StudentsListScreen
import com.example.loginflow.presentation.notification.NotificationsSettingsScreen
import com.example.loginflow.ui.theme.LoginFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginFlowTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "students"
                ) {
                    composable("students") {
                        StudentsListScreen(navController = navController)
                    }
                    composable("notifications") {
                        NotificationsSettingsScreen(navController = navController)
                    }
                }
            }
        }

    }
}
