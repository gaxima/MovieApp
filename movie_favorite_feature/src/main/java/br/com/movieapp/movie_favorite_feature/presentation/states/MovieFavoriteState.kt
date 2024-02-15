package br.com.movieapp.movie_favorite_feature.presentation.states

import br.com.movieapp.commons.model.Movie

data class MovieFavoriteState(
   val movies: List<Movie> = emptyList()
)
