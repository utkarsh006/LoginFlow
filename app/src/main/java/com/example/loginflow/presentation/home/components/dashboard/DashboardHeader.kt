package com.example.loginflow.presentation.home.components.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.loginflow.ui.theme.Black
import com.example.loginflow.ui.theme.FontSize16
import com.example.loginflow.ui.theme.FontSize28
import com.example.loginflow.ui.theme.Gray
import com.example.loginflow.R


@Composable
fun DashboardHeader(
    studentName: String,
    studentClass: String,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.hello_user, studentName),
                fontSize = FontSize28,
                fontWeight = FontWeight.Bold,
                color = Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.class_label, studentClass),
                fontSize = FontSize16,
                color = Gray,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = stringResource(R.string.notifications),
            modifier = Modifier
                .size(24.dp)
                .clickable { onNotificationClick() },
            tint = Black
        )
    }
}