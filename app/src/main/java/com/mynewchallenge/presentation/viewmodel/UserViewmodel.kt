package com.mynewchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow<ResultTypes<User>?>(null)
    val userState: StateFlow<ResultTypes<User>?> = _userState

    fun getAllUsers() {
        viewModelScope.launch {
            getUserUseCase().collect { result ->
                _userState.value = result
            }
        }
    }
}
