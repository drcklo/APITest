import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ucne.apitest.data.remote.dto.GamesDto
import com.ucne.apitest.presentation.games.GamesUIState
import com.ucne.apitest.presentation.games.GamesViewModel
import com.ucne.apitest.presentation.games.components.GameInfoDialog
import com.ucne.apitest.presentation.games.components.GameItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    viewModel: GamesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isInitialLoad by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedGame by remember { mutableStateOf<GamesDto?>(null) }

    GamesScreenBody(
        uiState = uiState,
        isInitialLoad = isInitialLoad,
        onFabClick = {
            if (isInitialLoad) {
                viewModel.getGames()
                isInitialLoad = false
            } else {
                viewModel.getGames()
            }
        },
        onGameClick = { game ->
            selectedGame = game
            showDialog = true
        }
    )
    if (showDialog && selectedGame != null) {
        GameInfoDialog(game = selectedGame!!) {
            showDialog = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreenBody(
    uiState: GamesUIState,
    isInitialLoad: Boolean,
    onFabClick: () -> Unit,
    onGameClick: (GamesDto) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Juegos Gratis")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(
                    if (isInitialLoad) Icons.Filled.Search else Icons.Filled.Refresh,
                    contentDescription = if (isInitialLoad) "Mostrar juegos" else "Actualizar juegos"
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
            } else if (uiState.errorMessage != null) {
                Text(
                    text = uiState.errorMessage,
                    color = Color.Red,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.games.sortedBy { it.id }) { game ->
                        GameItem(game = game, onClick = onGameClick)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GamesScreenBodyPreview() {
    val UiState = GamesUIState(
        isLoading = false,
        errorMessage = null,
        games = listOf(
            GamesDto(
                id = 1,
                title = "Game 1",
                publisher = "Publisher 1",
                shortDescription = "Short description 1",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 2,
                title = "Game 2",
                publisher = "Publisher 2",
                shortDescription = "Short description 2",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 3,
                title = "Game 3",
                publisher = "Publisher 3",
                shortDescription = "Short description 3",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 4,
                title = "Game 4",
                publisher = "Publisher 4",
                shortDescription = "Short description 4",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 5,
                title = "Game 5",
                publisher = "Publisher 5",
                shortDescription = "Short description 5",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 6,
                title = "Game 6",
                publisher = "Publisher 6",
                shortDescription = "Short description 6",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 7,
                title = "Game 7",
                publisher = "Publisher 7",
                shortDescription = "Short description 7",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 8,
                title = "Game 8",
                publisher = "Publisher 8",
                shortDescription = "Short description 8",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 9,
                title = "Game 9",
                publisher = "Publisher 9",
                shortDescription = "Short description 9",
                gameUrl = "https://www.google.com"
            ),
            GamesDto(
                id = 10,
                title = "Game 10",
                publisher = "Publisher 10",
                shortDescription = "Short description 10",
                gameUrl = "https://www.google.com"
            )
        )
    )

    GamesScreenBody(
        uiState = UiState,
        isInitialLoad = true,
        onFabClick = {},
        onGameClick = {}
    )
}