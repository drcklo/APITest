package com.ucne.apitest.data.remote.dto

import com.squareup.moshi.Json

data class GamesDTO(
    val id: Int,
    val title: String,
    val publisher: String,
    @Json(name = "short_description")
    val shortDescription: String,
    @Json(name = "game_url")
    val gameUrl: String
)