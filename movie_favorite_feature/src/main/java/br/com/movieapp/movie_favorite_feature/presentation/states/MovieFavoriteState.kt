package br.com.movieapp.movie_favorite_feature.presentation.states

import br.com.movieapp.commons.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieFavoriteState(
   val movies: Flow<List<Movie>> = emptyFlow()
)
