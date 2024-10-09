package com.mynewchallenge.data.service

import com.mynewchallenge.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("users?per_page=20&page=1")
    suspend fun getAllUsers(): User

//    @GET("/users/{userName}")
//    suspend fun getUser(
//        @Path("userName") userName: String
//    ): UserDto
}