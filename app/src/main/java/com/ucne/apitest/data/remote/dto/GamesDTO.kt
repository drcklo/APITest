package com.ucne.apitest.data.remote.dto

data class GamesDTO(
    val id: Int,
    val title: String,
    val publisher: String,
    val short_description: String,
    val game_url: String
)