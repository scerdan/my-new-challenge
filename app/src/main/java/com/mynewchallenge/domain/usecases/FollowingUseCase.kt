package com.mynewchallenge.domain.usecases

import com.mynewchallenge.data.service.GithubApiService
import com.mynewchallenge.data.serviceState.ResultTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FollowingUseCase @Inject constructor(private val apiService: GithubApiService) {
    operator fun invoke(username: String): Flow<ResultTypes<Int>?> = flow {
        try {
            emit(ResultTypes.Loading)

            // Obtén la lista de seguidos
            val following = apiService.getFollowing(username)
            emit(ResultTypes.Success(following.size))

        } catch (e: HttpException) {
            emit(ResultTypes.HttpError(e))
        } catch (e: IOException) {
            emit(ResultTypes.IOError(e))
        }
    }
}
