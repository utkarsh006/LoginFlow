package com.example.loginflow.data

import retrofit2.http.GET
import retrofit2.http.Url

interface StudentInfoApi {
    @GET
    suspend fun getStudentInfo(@Url url: String): StudentInfoDTO
}
