package com.example.loginflow.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginflow.presentation.home.StudentsListViewModel

@Composable
fun StudentsListScreen(
    viewModel: StudentsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Student Information
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Student Information",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Name: ${state.studentInfo.student.name}")
                        Text(text = "Class: ${state.studentInfo.student.`class`}")
                        Text(text = "Availability: ${state.studentInfo.student.availability.status}")
                        Text(text = "Quiz Attempts: ${state.studentInfo.student.quiz.attempts}")
                        Text(text = "Accuracy: ${state.studentInfo.student.accuracy.current}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Today's Summary
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Today's Summary",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Mood: ${state.studentInfo.todaySummary.mood}")
                        Text(text = "Description: ${state.studentInfo.todaySummary.description}")
                        Text(text = "Character Image: ${state.studentInfo.todaySummary.characterImage}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Recommended Video",
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Title: ${state.studentInfo.todaySummary.recommendedVideo.title}")
                        Text(text = "Action: ${state.studentInfo.todaySummary.recommendedVideo.actionText}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Weekly Overview
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Weekly Overview",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Overall Accuracy: ${state.studentInfo.weeklyOverview.overallAccuracy.percentage}% (${state.studentInfo.weeklyOverview.overallAccuracy.label})"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Quiz Streak:",
                            fontWeight = FontWeight.Bold
                        )
                        state.studentInfo.weeklyOverview.quizStreak.forEach { streak ->
                            Text(text = "  ${streak.day}: ${streak.status}")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Performance by Topic:",
                            fontWeight = FontWeight.Bold
                        )
                        state.studentInfo.weeklyOverview.performanceByTopic.forEach { topic ->
                            Text(text = "  ${topic.topic}: ${topic.trend}")
                        }
                    }
                }
            }
        }
    }
}
