package com.mynewchallenge.domain.usecases

import com.mynewchallenge.data.model.UserDetails
import com.mynewchallenge.data.model.toUserDetails
import com.mynewchallenge.data.repository.UserRepository
import com.mynewchallenge.data.serviceState.ResultTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetUserUseCase
@Inject
constructor(
    private val repository: UserRepository
) {

    operator fun invoke(userName: String): Flow<ResultTypes<UserDetails>> = flow {

        try {
            emit(ResultTypes.Loading)

            val user = repository.getUser(userName).toUserDetails()
            emit(ResultTypes.Success(user))

        } catch (e: HttpException) {
            emit(ResultTypes.HttpError(e))
        } catch (e: IOException) {
            emit(ResultTypes.IOError(e))
        } catch (e: HttpException) {
        }
    }
}

