package com.mynewchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.domain.usecases.GetUserSearch
import com.mynewchallenge.domain.usecases.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getUserSearch: GetUserSearch
) : ViewModel() {

    private val _userState = MutableStateFlow<ResultTypes<User>?>(null)
    val userState: StateFlow<ResultTypes<User>?> = _userState

    private val _userSearch = MutableStateFlow<ResultTypes<UserSearch>?>(null)
    val userSearch: StateFlow<ResultTypes<UserSearch>?> = _userSearch

    fun getAllUsers() {
        viewModelScope.launch {
            getUserUseCase().collect { result ->
                _userState.value = result
            }
        }
    }

    fun getSearchUser(userData: String) {
        viewModelScope.launch {
            getUserSearch(userData).collect { result ->
                _userSearch.value = result
            }
        }
    }
}
