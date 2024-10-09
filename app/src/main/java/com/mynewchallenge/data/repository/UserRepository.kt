package com.mynewchallenge.data.repository

import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserDto

interface UserRepository {
    suspend fun getAllUsers(): List<User>

}