package com.ucne.apitest.data.repository

import com.ucne.apitest.data.remote.GamesApi
import com.ucne.apitest.data.remote.dto.GamesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GamesRepository @Inject constructor(
    private val gamesApi: GamesApi
) {
    suspend fun getGames(): Flow<Resource<List<GamesDto>>> = flow {
        emit(Resource.Loading())
        try {
            val games = gamesApi.getGames()
            emit(Resource.Success(games))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}