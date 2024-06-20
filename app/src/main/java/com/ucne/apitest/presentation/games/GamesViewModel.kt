package com.ucne.apitest.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apitest.data.remote.dto.GamesDto
import com.ucne.apitest.data.repository.GamesRepository
import com.ucne.apitest.data.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesUIState())
    val uiState = _uiState.asStateFlow()
    fun getGames() {
        gamesRepository.getGames().onEach() { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }

                }

                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            games = result.data ?: emptyList()
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class GamesUIState(
    val games: List<GamesDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)