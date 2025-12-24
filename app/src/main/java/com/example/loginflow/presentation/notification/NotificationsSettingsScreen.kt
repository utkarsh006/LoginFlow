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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginflow.presentation.notification.components.NotificationItem
import com.example.loginflow.presentation.notification.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsSettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Notifications & Settings",
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
                text = "Notifications",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotificationItem(
                title = "Missed quiz in physics in yesterday",
                subtitle = "2 hours ago",
                accentColor = Color(0xFFFFA726),
                backgroundColor = Color(0xFFFFF7EC)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NotificationItem(
                title = "Badge earned",
                subtitle = "8 hours ago",
                accentColor = Color(0xFFAB47BC),
                backgroundColor = Color(0xFFF6EFF9)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NotificationItem(
                title = "Teacher Note",
                subtitle = "1 day ago",
                accentColor = Color(0xFF66BB6A),
                backgroundColor = Color(0xFFF1FAF1)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            SettingsItem(
                icon = Icons.Default.Person,
                title = "Switch Child",
                subtitle = "Change active child profile"
            )

            SettingsItem(
                icon = Icons.Default.Face,
                title = "Language",
                subtitle = "English"
            )

            SettingsItem(
                icon = Icons.Default.Delete,
                title = "Logout",
                subtitle = "Sign out of your account",
                iconTint = Color.Red
            )
        }
    }
}
