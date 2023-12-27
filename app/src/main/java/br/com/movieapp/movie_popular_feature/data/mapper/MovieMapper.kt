package br.com.movieapp.movie_popular_feature.data.mapper

import br.com.movieapp.core.data.remote.model.MoviesResult
import br.com.movieapp.core.domain.model.Movie
import br.com.movieapp.core.utils.toPostUrl

fun List<MoviesResult>.toMovie() = map{ moviesResult ->
    Movie(
        id = moviesResult.id,
        title = moviesResult.title,
        voteAverage = moviesResult.vote_average,
        imageUrl = moviesResult.poster_path.toPostUrl() ?: "",
    )
}