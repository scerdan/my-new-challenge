package com.mynewchallenge.data.service

import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.model.UserSearch
import com.mynewchallenge.data.model.following.FollowingData
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("users?per_page=20&page=1")
    suspend fun getAllUsers(): User

    @GET("/users/{userName}")
    suspend fun getUserSearch(
        @Path("userName") userName: String,
    ): UserSearch

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): FollowingData

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): FollowingData
}