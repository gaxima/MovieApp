package br.com.movieapp.movie_favorite_feature.data.mapper

import br.com.movieapp.commons.model.Movie
import br.com.movieapp.persistence.data.entity.MovieEntity

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.movieId,
        title = movieEntity.title,
        imageUrl = movieEntity.imageUrl,
    )
}

fun Movie.toMovieEntity() : MovieEntity{
    return MovieEntity(
        movieId = id,
        title = title,
        imageUrl = imageUrl,
    )
}
