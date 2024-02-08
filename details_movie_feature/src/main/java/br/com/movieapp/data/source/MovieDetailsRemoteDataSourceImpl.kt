package br.com.movieapp.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.network.data.remote.response.MoviesResponse
import br.com.movieapp.core.utils.toBackdropUrl
import br.com.movieapp.data.model.MovieDetails
import br.com.movieapp.data.paging.MovieSimilarPagingSource
import br.com.movieapp.domain.source.MovieDetailsRemoteDataSource
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieDetailsRemoteDataSource {

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map{
            it.name
        }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            backdropPathUrl = response.backdrop_path.toBackdropUrl(),
            releaseDate = response.release_date,
            voteAverage = response.vote_average,
            duration = response.runtime,
            voteCount = response.vote_count
        )
    }

    override suspend fun getMoviesSimilar(movieId: Int, page: Int): MoviesResponse {
        return service.getMoviesSimilar(page = page, movieId = movieId)
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource {
        return MovieSimilarPagingSource(
            remoteDataSource = this,
            movieId = movieId)
    }
}