package com.ucne.apitest.presentation.games.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.ucne.apitest.data.remote.dto.GameDto

@Composable
fun GameInfoDialog(game: GameDto, onDismiss: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(text = game.title)
    }, text = {
        Column {
            Text(text = game.shortDescription)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Más información", color = Color.Blue, modifier = Modifier.clickable {
                uriHandler.openUri(game.gameUrl)
            })
        }
    }, confirmButton = {
        Button(onClick = onDismiss) {
            Text("Cerrar")
        }
    })
}