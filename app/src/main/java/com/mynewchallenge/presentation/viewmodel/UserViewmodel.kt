package com.mynewchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.domain.repository.TokenRepository
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
    private val getUserSearch: GetUserSearch,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<ResultTypes<User>?>(ResultTypes.Loading)
    val userState: StateFlow<ResultTypes<User>?> = _userState

    private val _userSearch = MutableStateFlow<ResultTypes<UserSearch>?>(ResultTypes.Loading)
    val userSearch: StateFlow<ResultTypes<UserSearch>?> = _userSearch

    init {
        viewModelScope.launch {
            tokenRepository.token.collectLatest { token ->
                if (token != null) {
                    getAllUsers()
                } else {
                    _userState.value = ResultTypes.Loading
                }
            }
        }
    }

    private fun getAllUsers() {
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
