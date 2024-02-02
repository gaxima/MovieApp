package br.com.movieapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.core.utils.UtilsFunctions
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.presentation.state.MovieDetailsState
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    fun getMovieDetails(getMovieDetails: MovieDetailsEvent.GetMovieDetails) {
        event(getMovieDetails)
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {
            is MovieDetailsEvent.GetMovieDetails -> {
                viewModelScope.launch {
                    movieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collect() { resultData ->
                        when (resultData) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetails = resultData.data?.second,
                                    results = resultData.data?.first ?: emptyFlow()
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    isError = resultData.e?.message.toString()
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    isError = resultData.e?.message.toString()
                                )
                                UtilsFunctions.logError(
                                    "DETAIL_ERROR",
                                    resultData.e?.message.toString()
                                )
                            }

                            is ResultData.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}