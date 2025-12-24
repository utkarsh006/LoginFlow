package com.example.loginflow.presentation.components.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.loginflow.data.StudentInfoDTO
import com.example.loginflow.presentation.components.summary.TodaySummaryCard
import com.example.loginflow.presentation.components.weeklyoverview.WeeklyOverviewSection

@Composable
fun StudentDashboardContent(
    navController: NavController,
    studentInfo: StudentInfoDTO
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        // Header Section
        DashboardHeader(
            studentName = studentInfo.student.name,
            studentClass = studentInfo.student.`class`,
            onNotificationClick = {
                navController.navigate("notifications")
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Stats Cards Row
        StatsCardsRow(
            availability = studentInfo.student.availability.status,
            quizAttempts = studentInfo.student.quiz.attempts,
            accuracy = studentInfo.student.accuracy.current
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Today's Summary
        TodaySummaryCard(
            mood = studentInfo.todaySummary.mood,
            description = studentInfo.todaySummary.description,
            videoTitle = studentInfo.todaySummary.recommendedVideo.title,
            videoAction = studentInfo.todaySummary.recommendedVideo.actionText
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Weekly Overview
        WeeklyOverviewSection(weeklyOverview = studentInfo.weeklyOverview)

        // Add bottom padding for navigation bar and extra spacing
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
        )
    }
}