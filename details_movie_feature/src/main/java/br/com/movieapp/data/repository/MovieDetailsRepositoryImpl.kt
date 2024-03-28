package br.com.movieapp.data.repository


import androidx.paging.PagingSource
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.data.model.MovieDetails
import br.com.movieapp.data.paging.MovieSimilarPagingSource
import br.com.movieapp.domain.repository.MovieDetailsRepository
import br.com.movieapp.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetails(movieId)
    }

    override fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(
            remoteDataSource = remoteDataSource,
            movieId = movieId
        )
    }
}