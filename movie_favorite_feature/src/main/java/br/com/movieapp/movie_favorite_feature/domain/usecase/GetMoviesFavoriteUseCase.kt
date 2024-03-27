package br.com.movieapp.movie_favorite_feature.domain.usecase

import br.com.movieapp.commons.model.Movie
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface GetMoviesFavoriteUseCase {
    suspend operator fun invoke(): Flow<List<Movie>>
}

class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    private val repository: MovieFavoriteRepository
) : GetMoviesFavoriteUseCase {


    override suspend fun invoke(): Flow<List<Movie>> {
        return try {
            repository.getFavoriteMovies()
        } catch (e: Exception) {
            emptyFlow()
        }
    }
}
