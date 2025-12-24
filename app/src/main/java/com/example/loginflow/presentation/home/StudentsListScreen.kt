package com.example.loginflow.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.loginflow.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.loginflow.data.StudentInfoDTO

@Composable
fun StudentsListScreen(
    navController: NavController,
    viewModel: StudentsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        } else if (state.studentInfo != null) {
            StudentDashboardContent(navController, studentInfo = state.studentInfo)
        }
    }
}

@Composable
private fun StudentDashboardContent(
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
        TodaysSummaryCard(
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

@Composable
private fun DashboardHeader(
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
                text = "Hello $studentName!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${studentClass}th Class",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            modifier = Modifier
                .size(24.dp)
                .clickable { onNotificationClick() },
            tint = Color.Black
        )
    }
}

@Composable
private fun StatsCardsRow(
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
            backgroundColor = Color(0xFFE8F5E9),
            iconContent = "✓"
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Quiz",
            value = "$quizAttempts Attempt",
            backgroundColor = Color(0xFFFFF3E0),
            iconContent = "?"
        )

        StatCard(
            modifier = Modifier.weight(1f),
            title = "Accuracy",
            value = accuracy,
            backgroundColor = Color(0xFFFDECEA),
            iconContent = "🎯"
        )
    }
}


@Composable
private fun StatCard(
    title: String,
    value: String,
    backgroundColor: Color,
    iconContent: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = value,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = iconContent, fontSize = 18.sp)
            }
        }
    }
}

@Composable
private fun TodaysSummaryCard(
    mood: String,
    description: String,
    videoTitle: String,
    videoAction: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Today's Summary",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFCF7FF)
            ),
            border = BorderStroke(1.dp, Color(0xFFB39DDB)),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Character
                Icon(
                    painter = painterResource(id = R.drawable.ic_focused),
                    contentDescription = null,
                    modifier = Modifier.size(72.dp),
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Mood
                Text(
                    text = mood,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7E57C2)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // CTA Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black)
                        .clickable { }
                        .padding(vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color.White
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = videoAction,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
private fun WeeklyOverviewSection(
    weeklyOverview: StudentInfoDTO.WeeklyOverview,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "Weekly Overview",
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

@Composable
private fun QuizStreakCard(
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quiz Streak",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_quiz_streak),
                    contentDescription = "Quiz streak icon",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display all days in order
                quizStreak.forEach { streak ->
                    val isCompleted = streak.status.equals("completed", ignoreCase = true) ||
                            streak.status.equals("present", ignoreCase = true)

                    if (isCompleted) {
                        // Completed day - green checkmark
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF4CAF50)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "✓",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        // Incomplete day - dashed circle with day letter
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray.copy(alpha = 0.3f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = streak.day.take(1).uppercase(),
                                fontSize = 12.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AccuracyCard(
    overallAccuracy: StudentInfoDTO.WeeklyOverview.OverallAccuracy,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Accuracy",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${overallAccuracy.percentage}% correct",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = overallAccuracy.percentage / 100f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFFF44336),
                    trackColor = Color.Gray.copy(alpha = 0.2f)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_accuracy_weekly),
                contentDescription = "Quiz streak icon",
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun PerformanceByTopicCard(
    performanceByTopic: List<StudentInfoDTO.WeeklyOverview.PerformanceByTopic>,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Performance by Topic",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_performance_weekly),
                    contentDescription = "Quiz streak icon",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            performanceByTopic.forEach { topic ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = topic.topic,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = topic.trend,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
