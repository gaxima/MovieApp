package br.com.movieapp.movie_favorite_feature.domain.source

import br.com.movieapp.commons.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteLocalDataSource {

    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean

}