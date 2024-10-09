package com.mynewchallenge.domain.usecases

import com.mynewchallenge.data.model.User
import com.mynewchallenge.data.repository.UserRepository
import com.mynewchallenge.data.service.GithubApiService
import com.mynewchallenge.data.serviceState.ResultTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val apiService: GithubApiService) {

    operator fun invoke(): Flow<ResultTypes<List<User>>?> = flow {
        try {
            emit(ResultTypes.Loading)

            val userResult = apiService.getAllUsers()
            emit(ResultTypes.Success(userResult))

        } catch (e: HttpException) {
            emit(ResultTypes.HttpError(e))
        } catch (e: IOException) {
            emit(ResultTypes.IOError(e))
        } catch (e: HttpException) {
        }
    }
}

