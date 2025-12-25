package com.example.loginflow.presentation.home.components.weeklyoverview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflow.R
import com.example.loginflow.data.StudentInfoDTO

@Composable
fun WeeklyOverviewSection(
    weeklyOverview: StudentInfoDTO.WeeklyOverview,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.weekly_overview),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Quiz Streak
        QuizStreakCard(quizStreak = weeklyOverview.quizStreak)

        Spacer(modifier = Modifier.height(16.dp))

        // Accuracy Card
        AccuracyCard(overallAccuracy = weeklyOverview.overallAccuracy)

        Spacer(modifier = Modifier.height(16.dp))

        // Performance by Topic
        PerformanceByTopicCard(performanceByTopic = weeklyOverview.performanceByTopic)
    }
}