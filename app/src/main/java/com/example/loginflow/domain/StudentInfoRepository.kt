package com.example.loginflow.domain

import com.example.loginflow.data.StudentInfoDTO

interface StudentInfoRepository {
    suspend fun getStudentInfo(): StudentInfoDTO
}