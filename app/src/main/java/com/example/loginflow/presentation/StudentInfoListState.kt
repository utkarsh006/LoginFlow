package com.example.loginflow.presentation

import com.example.loginflow.data.StudentInfoDTO

data class StudentInfoListState(
    val isLoading: Boolean = false,
    val studentInfo: StudentInfoDTO? = null,
    val error: String = ""
)
