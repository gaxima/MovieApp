package br.com.movieapp.presentation

import br.com.movieapp.commons.model.Movie

sealed class MovieDetailsEvent {

    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()

    data class AddFavorite(val movie: Movie) : MovieDetailsEvent()

    data class CheckedFavorite(val movieId: Int) : MovieDetailsEvent()

    data class RemoveFavorite(val movie: Movie) : MovieDetailsEvent()
}