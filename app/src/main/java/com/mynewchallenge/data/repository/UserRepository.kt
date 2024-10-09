package com.mynewchallenge.data.repository

import com.mynewchallenge.data.model.User

interface UserRepository {
    suspend fun getAllUsers(): User

}