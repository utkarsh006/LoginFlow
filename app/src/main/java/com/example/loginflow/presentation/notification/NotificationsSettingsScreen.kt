package com.example.loginflow.presentation.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginflow.R
import com.example.loginflow.presentation.authentication.AuthViewModel
import com.example.loginflow.presentation.notification.components.NotificationItem
import com.example.loginflow.presentation.notification.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsSettingsScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.notifications_settings),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                actions = {
                    // Empty actions to balance center alignment
                    Spacer(modifier = Modifier.width(48.dp))
                }
            )

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {

            Text(
                text = stringResource(R.string.notifications),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotificationItem(
                title = stringResource(R.string.missed_quiz_notification),
                subtitle = stringResource(R.string.two_hours_ago),
                accentColor = Color(0xFFFFA726),
                backgroundColor = Color(0xFFFFF7EC)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NotificationItem(
                title = stringResource(R.string.badge_earned),
                subtitle = stringResource(R.string.eight_hours_ago),
                accentColor = Color(0xFFAB47BC),
                backgroundColor = Color(0xFFF6EFF9)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NotificationItem(
                title = stringResource(R.string.teacher_note),
                subtitle = stringResource(R.string.one_day_ago),
                accentColor = Color(0xFF66BB6A),
                backgroundColor = Color(0xFFF1FAF1)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.settings),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingsItem(
                icon = Icons.Default.Person,
                title = stringResource(R.string.switch_child),
                subtitle = stringResource(R.string.change_active_child_profile)
            )

            SettingsItem(
                icon = Icons.Default.Face,
                title = stringResource(R.string.language),
                subtitle = stringResource(R.string.english)
            )

            SettingsItem(
                icon = Icons.Default.Delete,
                title = stringResource(R.string.logout),
                subtitle = stringResource(R.string.sign_out_account),
                iconTint = Color.Red,
                onClick = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
