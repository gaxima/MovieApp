package br.com.movieapp.core.network.data.remote.response

import br.com.movieapp.commons.model.Movie

data class MoviePaging(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<Movie>
)


