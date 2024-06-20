package com.ucne.apitest.data.remote

import com.ucne.apitest.data.remote.dto.GameDto
import com.ucne.apitest.util.Constants.Companion.GAMES_ENDPOINT
import retrofit2.http.GET

interface GameApi {
    @GET(GAMES_ENDPOINT)
    suspend fun getGames(): List<GameDto>
}