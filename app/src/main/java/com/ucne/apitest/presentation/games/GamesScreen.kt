import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.apitest.presentation.games.GamesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isInitialLoad by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Lista de Juegos")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (isInitialLoad) {
                        viewModel.getGames()
                        isInitialLoad = false
                    } else {
                        viewModel.getGames()
                    }
                }
            ) {
                Icon(
                    if (isInitialLoad) Icons.Filled.Add else Icons.Filled.Refresh,
                    contentDescription = if (isInitialLoad) "Llamar juegos" else "Actualizar juegos"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "ID", modifier = Modifier.weight(0.3f))
                Text(text = "Título", modifier = Modifier.weight(0.7f))
                Text(text = "Publisher", modifier = Modifier.weight(1f))
            }

            HorizontalDivider()

            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else if (uiState.errorMessage != null) {
                Text(text = uiState.errorMessage!!, color = Color.Red, modifier = Modifier.fillMaxWidth())
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.games.sortedBy { it.id }) { game ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* TODO*/ }
                                .padding(vertical = 8.dp)
                        ) {
                            Text(text = game.id.toString(), modifier = Modifier.weight(0.3f))
                            Text(text = game.title, modifier = Modifier.weight(0.7f))
                            Text(text = game.publisher, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
