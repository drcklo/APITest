package com.ucne.apitest.presentation.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apitest.data.remote.dto.GameDto
import com.ucne.apitest.data.repository.GameRepository
import com.ucne.apitest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState = _uiState.asStateFlow()
    fun getGames() {
        gameRepository.getGames().onEach { result ->
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

data class GameUiState(
    val games: List<GameDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)