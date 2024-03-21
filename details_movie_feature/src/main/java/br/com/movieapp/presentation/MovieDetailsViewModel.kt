package br.com.movieapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.cmore.network.utils.Constants
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.core.utils.UtilsFunctions
import br.com.movieapp.data.model.MovieDetails
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.presentation.state.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val addMovieFavorite: AddMovieFavoriteUseCase,
    private val deleteMovieFavorite: DeleteMovieFavoriteUseCase,
    private val isMovieFavorite: IsMovieFavoriteUseCase,
    savedStateHandler: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailsState())
        private set

    private val movieId = savedStateHandler.get<Int>(key = Constants.MOVIE_DETAILS_ARGUMENT_KEY)

    init {
        movieId?.let { safeMovieId ->
            checkedFavorite(MovieDetailsEvent.CheckedFavorite(movieId = safeMovieId))
//            getMovieDetails(MovieDetailsEvent.GetMovieDetails(movieId = safeMovieId))
        }
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailsEvent.CheckedFavorite) {
        event(checkedFavorite)
    }

    private fun getMovieDetails(getMovieDetails: MovieDetailsEvent.GetMovieDetails) {
        event(getMovieDetails)
    }

    fun onAddFavorite(movie: Movie) {
        if (uiState.iconColor == Color.White) {
            event(MovieDetailsEvent.AddFavorite(movie = movie))
        } else {
            event(MovieDetailsEvent.RemoveFavorite(movie = movie))
        }
    }

    private fun event(event: MovieDetailsEvent) {
        when (event) {

            is MovieDetailsEvent.AddFavorite -> {
                viewModelScope.launch {
                    addMovieFavorite.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.Red
                                )
                            }

                            is ResultData.Failure -> {
                                UtilsFunctions.logError("DETAIL", "Error to add favorite")
                            }

                            is ResultData.Loading -> {}

                        }

                    }
                }
            }

            is MovieDetailsEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavorite.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = if (result.data) Color.Red else Color.White
                                )
                            }

                            is ResultData.Failure -> {
                                UtilsFunctions.logError("DETAIL", "Error to checked favorite")
                            }

                            is ResultData.Loading -> {}

                        }
                    }
                }

            }

            is MovieDetailsEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    deleteMovieFavorite.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.White
                                )
                            }

                            is ResultData.Failure -> {
                                UtilsFunctions.logError("DETAIL", "Error to remove favorite")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }

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
                                    results = resultData.data.first ?: emptyFlow(),
                                )
                            }

                            is ResultData.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    isError = resultData.e?.message.toString(),
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