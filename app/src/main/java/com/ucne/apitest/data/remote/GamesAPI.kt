package com.ucne.apitest.data.remote

import com.ucne.apitest.data.remote.dto.GamesDTO
import retrofit2.http.GET

interface GamesAPI {
    @GET("games")
    suspend fun getGames() : List<GamesDTO>
}