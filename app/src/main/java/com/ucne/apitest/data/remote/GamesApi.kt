package com.ucne.apitest.data.remote

import com.ucne.apitest.data.remote.dto.GamesDto
import retrofit2.http.GET

interface GamesApi {
    @GET("games")
    suspend fun getGames() : List<GamesDto>
}