package com.mynewchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<ResultTypes<List<User>>?>(null)
    val userState: StateFlow<ResultTypes<List<User>>?> = _userState

    fun getUser(userName: String) {
        getUserUseCase(userName).onEach { result ->
            when (result) {
                is ResultTypes.Success -> {
                    _userState.value = ResultTypes.Success(result.data)
                }
                is ResultTypes.Error -> {
                    _userState.value = ResultTypes.Error(result.exception)
                }
                is ResultTypes.HttpError -> {
                    _userState.value = ResultTypes.HttpError(result.exception)
                }
                is ResultTypes.IOError -> {
                    _userState.value = ResultTypes.IOError(result.exception)
                }
                is ResultTypes.Loading -> {
                    _userState.value = ResultTypes.Loading
                }

                else -> {}

            }
        }.launchIn(viewModelScope)
    }
}
