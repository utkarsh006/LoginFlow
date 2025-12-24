package com.example.loginflow.data

data class StudentInfoDTO(
    val student: Student,
    val todaySummary: TodaySummary,
    val weeklyOverview: WeeklyOverview
) {
    data class Student(
        val accuracy: Accuracy,
        val availability: Availability,
        val `class`: String,
        val name: String,
        val quiz: Quiz
    ) {
        data class Accuracy(
            val current: String
        )

        data class Availability(
            val status: String
        )

        data class Quiz(
            val attempts: Int
        )
    }

    data class TodaySummary(
        val characterImage: String,
        val description: String,
        val mood: String,
        val recommendedVideo: RecommendedVideo
    ) {
        data class RecommendedVideo(
            val actionText: String,
            val title: String
        )
    }

    data class WeeklyOverview(
        val overallAccuracy: OverallAccuracy,
        val performanceByTopic: List<PerformanceByTopic>,
        val quizStreak: List<QuizStreak>
    ) {
        data class OverallAccuracy(
            val label: String,
            val percentage: Int
        )

        data class PerformanceByTopic(
            val topic: String,
            val trend: String
        )

        data class QuizStreak(
            val day: String,
            val status: String
        )
    }
}