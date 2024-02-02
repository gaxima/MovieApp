package br.com.movieapp.presentation

sealed class MovieDetailsEvent {

    data class GetMovieDetails(val movieId: Int) : MovieDetailsEvent()
}