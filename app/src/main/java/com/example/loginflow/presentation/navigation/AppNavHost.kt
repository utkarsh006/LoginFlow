package com.example.loginflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.loginflow.presentation.authentication.AuthViewModel
import com.example.loginflow.presentation.authentication.LoginPage
import com.example.loginflow.presentation.authentication.SignUpPage
import com.example.loginflow.presentation.home.StudentsListScreen
import com.example.loginflow.presentation.notification.NotificationsSettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.LOGIN) {
            LoginPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Routes.SIGNUP) {
            SignUpPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Routes.STUDENTS) {
            StudentsListScreen(navController = navController)
        }

        composable(Routes.NOTIFICATIONS) {
            NotificationsSettingsScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}
