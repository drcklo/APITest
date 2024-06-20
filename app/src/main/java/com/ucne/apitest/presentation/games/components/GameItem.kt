package com.ucne.apitest.presentation.games.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucne.apitest.data.remote.dto.GameDto

@Composable
fun GameItem(
    game: GameDto,
    onClick: (GameDto) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(game) }
            .padding(vertical = 8.dp)
    ) {
        Text(text = game.id.toString(), modifier = Modifier.weight(0.3f))
        Text(text = game.title, modifier = Modifier.weight(0.7f))
        Text(text = game.publisher, modifier = Modifier.weight(1f))
    }
}