package com.example.loginflow.data

import com.example.loginflow.common.Constants
import com.example.loginflow.domain.StudentInfoRepository
import javax.inject.Inject

class StudentInfoRepoImpl @Inject constructor(
    private val studentInfoApi: StudentInfoApi
) : StudentInfoRepository {

    override suspend fun getStudentInfo(): StudentInfoDTO {
        return studentInfoApi.getStudentInfo(Constants.STUDENT_DASHBOARD_URL)
    }
}
