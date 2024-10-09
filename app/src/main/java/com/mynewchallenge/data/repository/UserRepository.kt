package com.mynewchallenge.data.repository

import com.mynewchallenge.data.model.UserDto
import com.mynewchallenge.data.model.UsersListDto

interface UserRepository {

    suspend fun getUser(userName: String): UserDto

    suspend fun getUserFollowers(userName: String): List<UserDto>

    suspend fun getUserFollowing(userName: String): List<UserDto>

    suspend fun getAllUserTest(): List<UsersListDto>

}