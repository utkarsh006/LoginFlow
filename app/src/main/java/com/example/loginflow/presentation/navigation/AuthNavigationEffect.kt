package com.example.loginflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.loginflow.presentation.authentication.AuthState

@Composable
fun IsAuthenticatedNavigationEffect(
    authState: AuthState,
    navController: NavController
) {
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated ->
                navController.navigateAndClear(Routes.STUDENTS)

            is AuthState.Unauthenticated ->
                navController.navigateAndClear(Routes.LOGIN)

            else -> Unit
        }
    }
}

private fun NavController.navigateAndClear(route: String) {
    if (currentDestination?.route == route) return

    navigate(route) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}
