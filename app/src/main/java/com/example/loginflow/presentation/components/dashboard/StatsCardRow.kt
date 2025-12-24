package com.example.loginflow.presentation.components.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflow.R

@Composable
fun StatsCardsRow(
    availability: String,
    quizAttempts: Int,
    accuracy: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(
            modifier = Modifier.weight(1f),
            title = "Availability",
            value = availability,
            borderColor = Color(0xFF4CAF50),
            backgroundTint = Color(0xFFEAF7EA),
            iconRes = R.drawable.ic_available
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Quiz",
            value = "$quizAttempts Attempt",
            borderColor = Color(0xFFFF9800),
            backgroundTint = Color(0xFFFFF4E5),
            iconRes = R.drawable.ic_quiz
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Accuracy",
            value = accuracy,
            borderColor = Color(0xFFF44336),
            backgroundTint = Color(0xFFFFEEEE),
            iconRes = R.drawable.ic_accuracy
        )

    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    borderColor: Color,
    backgroundTint: Color,
    iconRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundTint
        ),
        border = BorderStroke(1.5.dp, borderColor),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.size(26.dp),
                tint = borderColor
            )

            Column {
                Text(
                    text = title,
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = value,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = borderColor
                )
            }
        }
    }
}