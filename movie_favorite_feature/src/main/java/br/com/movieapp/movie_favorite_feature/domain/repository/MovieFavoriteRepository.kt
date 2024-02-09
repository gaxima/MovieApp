package br.com.movieapp.movie_favorite_feature.domain.repository

import br.com.movieapp.commons.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieFavoriteRepository {

    fun getFavoriteMovies(): Flow<List<Movie>>

    suspend fun insert(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean
}