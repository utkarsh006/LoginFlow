package com.example.loginflow.domain

import com.example.loginflow.common.Resource
import com.example.loginflow.data.StudentInfoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetStudentsInfoUseCase @Inject constructor(
    private val repository: StudentInfoRepository,
) {
    operator fun invoke(): Flow<Resource<StudentInfoDTO>> = flow {
        try {
            emit(Resource.Loading())
            val studentInfo = repository.getStudentInfo()
            emit(Resource.Success(studentInfo))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach Server"))
        }
    }
}