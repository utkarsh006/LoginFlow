package com.example.loginflow.di

import com.example.loginflow.common.Constants
import com.example.loginflow.data.StudentInfoApi
import com.example.loginflow.data.StudentInfoRepoImpl
import com.example.loginflow.domain.StudentInfoRepository
import com.example.meal.domain.usecases.get_meals.GetStudentsInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): StudentInfoApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StudentInfoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideStudentInfoRepository(studentInfoApi: StudentInfoApi): StudentInfoRepository {
        return StudentInfoRepoImpl(studentInfoApi)
    }

    @Provides
    @Singleton
    fun provideStudentsInfoUseCase(studentInfoRepository: StudentInfoRepository) : GetStudentsInfoUseCase{
        return GetStudentsInfoUseCase(studentInfoRepository)
    }
}