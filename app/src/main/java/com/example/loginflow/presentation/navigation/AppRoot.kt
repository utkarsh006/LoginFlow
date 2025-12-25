package com.example.loginflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.loginflow.presentation.authentication.AuthState
import com.example.loginflow.presentation.authentication.AuthViewModel

@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    val authState by authViewModel
        .authState
        .collectAsStateWithLifecycle()

    IsAuthenticatedNavigationEffect(
        authState = authState,
        navController = navController
    )

    AppNavHost(
        navController = navController,
        authViewModel = authViewModel,
        startDestination = authState.startDestination()
    )
}

private fun AuthState.startDestination(): String =
    when (this) {
        is AuthState.Authenticated -> Routes.STUDENTS
        else -> Routes.LOGIN
    }
