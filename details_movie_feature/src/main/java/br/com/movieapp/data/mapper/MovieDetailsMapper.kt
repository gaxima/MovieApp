package br.com.movieapp.data.mapper

import br.com.movieapp.core.network.model.Movie
import br.com.movieapp.data.model.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        voteAverage = this.voteAverage,
        imageUrl = this.backdropPathUrl.toString()
    )

}