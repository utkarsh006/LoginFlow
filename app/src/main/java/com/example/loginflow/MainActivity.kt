package com.example.loginflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginflow.presentation.authentication.AuthState
import com.example.loginflow.presentation.authentication.AuthViewModel
import com.example.loginflow.presentation.authentication.LoginPage
import com.example.loginflow.presentation.authentication.SignUpPage
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
                val authViewModel = viewModel<AuthViewModel>()
                val authState = authViewModel.authState.collectAsStateWithLifecycle().value

                LaunchedEffect(authState) {
                    when (authState) {
                        is AuthState.Authenticated -> {
                            if (navController.currentDestination?.route != "students") {
                                navController.navigate("students") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        }
                        is AuthState.Unauthenticated -> {
                            if (navController.currentDestination?.route != "login") {
                                navController.navigate("login") {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        }
                        else -> {}
                    }
                }

                NavHost(
                    navController = navController,
                    startDestination = if (authState is AuthState.Authenticated) "students" else "login"
                ) {
                    composable("login") {
                        LoginPage(
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }
                    composable("signup") {
                        SignUpPage(
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }
                    composable("students") {
                        StudentsListScreen(navController = navController)
                    }
                    composable("notifications") {
                        NotificationsSettingsScreen(
                            navController = navController,
                            authViewModel = authViewModel
                        )
                    }
                }
            }
        }

    }
}
