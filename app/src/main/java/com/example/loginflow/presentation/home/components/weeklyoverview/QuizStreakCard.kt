package com.example.loginflow.presentation.home.components.weeklyoverview

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflow.R
import com.example.loginflow.data.StudentInfoDTO

@Composable
fun QuizStreakCard(
    quizStreak: List<StudentInfoDTO.WeeklyOverview.QuizStreak>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            QuizSectionHeader()

            HorizontalDivider(color = Color(0xFFE0E0E0), thickness = 1.dp)

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                quizStreak.forEach { streak ->
                    QuizStreakDay(
                        day = streak.day,
                        isCompleted = streak.status == "done"
                    )
                }
            }
        }
    }
}

@Composable
private fun QuizSectionHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.quiz_streak),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_quiz_streak),
            contentDescription = stringResource(R.string.quiz_streak_icon),
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun QuizStreakDay(
    day: String,
    isCompleted: Boolean
) {
    val baseModifier = Modifier
        .size(32.dp)
        .clip(CircleShape)

    Box(
        modifier = if (isCompleted) {
            baseModifier.background(Color(0xFF4CAF50))
        } else {
            baseModifier.border(
                width = 2.dp,
                color = Color.Gray.copy(alpha = 0.3f),
                shape = CircleShape
            )
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isCompleted) "✓" else day.take(1).uppercase(),
            color = if (isCompleted) Color.White else Color.Gray,
            fontSize = if (isCompleted) 16.sp else 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}