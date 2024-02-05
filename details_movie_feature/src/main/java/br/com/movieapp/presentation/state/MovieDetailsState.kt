package br.com.movieapp.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import br.com.movieapp.core.network.model.Movie
import br.com.movieapp.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailsState(
    val movieDetails: MovieDetails? = null,
    val isLoading: Boolean = false,
    val isError: String = "",
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()

)
