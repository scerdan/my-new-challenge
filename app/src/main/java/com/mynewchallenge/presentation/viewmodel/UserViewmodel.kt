package com.mynewchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.serviceState.ResultTypes
import com.mynewchallenge.domain.repository.TokenRepository
import com.mynewchallenge.domain.usecases.FollowersUseCase
import com.mynewchallenge.domain.usecases.FollowingUseCase
import com.mynewchallenge.domain.usecases.UserSearchUseCase
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
    private val userSearchUseCase: UserSearchUseCase,
    private val followersUseCase: FollowersUseCase,
    private val followingUseCase: FollowingUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<ResultTypes<User>?>(ResultTypes.Loading)
    val userState: StateFlow<ResultTypes<User>?> = _userState

    private val _userSearch = MutableStateFlow<ResultTypes<UserSearch>?>(ResultTypes.Loading)
    val userSearch: StateFlow<ResultTypes<UserSearch>?> = _userSearch

    private val _followersCount = MutableStateFlow<ResultTypes<Int>?>(ResultTypes.Loading)
    val followersCount: StateFlow<ResultTypes<Int>?> = _followersCount

    private val _followingCount = MutableStateFlow<ResultTypes<Int>?>(ResultTypes.Loading)
    val followingCount: StateFlow<ResultTypes<Int>?> = _followingCount

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
            userSearchUseCase(userData).collect { result ->
                _userSearch.value = result
            }
        }
    }

    fun fetchFollowersAndFollowing(username: String) {
        viewModelScope.launch {
            followersUseCase(username).collect { result ->
                when (result) {
                    is ResultTypes.Loading -> {
                        // Maneja el estado de carga si es necesario
                    }
                    is ResultTypes.Success -> {
                        _followersCount.value = result
                    }
                    is ResultTypes.HttpError -> {
                        // Maneja el error HTTP
                    }
                    is ResultTypes.IOError -> {
                        // Maneja el error de IO
                    }

                    is ResultTypes.Error -> TODO()
                    null -> TODO()
                }
            }

            followingUseCase(username).collect { result ->
                when (result) {
                    is ResultTypes.Loading -> {
                        // Maneja el estado de carga si es necesario
                    }
                    is ResultTypes.Success -> {
                        _followingCount.value = result
                    }
                    is ResultTypes.HttpError -> {
                        // Maneja el error HTTP
                    }
                    is ResultTypes.IOError -> {
                        // Maneja el error de IO
                    }

                    is ResultTypes.Error -> TODO()
                    null -> TODO()
                }
            }
        }
    }
}
