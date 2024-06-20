package com.ucne.apitest.data.repository

import android.util.Log
import com.ucne.apitest.data.remote.GameApi
import com.ucne.apitest.data.remote.dto.GameDto
import com.ucne.apitest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameApi: GameApi
) {
    fun getGames(): Flow<Resource<List<GameDto>>> = flow {
        emit(Resource.Loading())
        try {
            val games = gameApi.getGames()
            emit(Resource.Success(games))
        } catch (e: Exception) {
            Log.e("GameRepository", "getGames: ${e.message}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}

