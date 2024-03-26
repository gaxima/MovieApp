package br.com.movieapp.core.network.mapper

import android.app.appsearch.SearchResult
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.core.data.remote.model.MoviesResult
import br.com.movieapp.core.utils.toPostUrl

fun MoviesResult.toMovie() = Movie(
    id = id,
    title = title,
    voteAverage = vote_average,
    imageUrl = poster_path.toPostUrl()
)
fun List<MoviesResult>.toMovie() = map { moviesResult ->
    Movie(
        id = moviesResult.id,
        title = moviesResult.title,
        voteAverage = moviesResult.vote_average,
        imageUrl = moviesResult.poster_path.toPostUrl(),
    )
}