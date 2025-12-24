package com.example.loginflow.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginflow.common.Resource
import com.example.meal.domain.usecases.get_meals.GetStudentsInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StudentsListViewModel @Inject constructor(
    private val getStudentsInfoUseCase: GetStudentsInfoUseCase
) : ViewModel() {

    private val _state = mutableStateOf(StudentInfoListState())
    val state: State<StudentInfoListState> get() = _state // exposing this state to the composables

    init {
        getStudentInfo()
    }

    private fun getStudentInfo() {
        getStudentsInfoUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = StudentInfoListState(studentInfo = result.data)
                }

                is Resource.Error -> {
                    _state.value =
                        StudentInfoListState(error = result.message ?: "Unexpected Error")
                }

                is Resource.Loading -> {
                    _state.value = StudentInfoListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}