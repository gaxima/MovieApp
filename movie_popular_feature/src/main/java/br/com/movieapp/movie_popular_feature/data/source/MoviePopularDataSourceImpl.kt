package br.com.movieapp.movie_popular_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.network.data.remote.response.MoviePaging
import br.com.movieapp.core.network.data.remote.response.MoviesResponse
import br.com.movieapp.core.network.mapper.toMovie
import br.com.movieapp.movie_popular_feature.data.paging.MoviePagingSource
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import javax.inject.Inject


class MoviePopularDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(this)
    }

    override suspend fun getPopularMovies(page: Int): MoviePaging {

        val response = service.getPopularMovies(page)

        return MoviePaging(
            page = response.page,
            totalPages = response.total_pages,
            totalResults = response.total_results,
            movies = response.results.map {
                it.toMovie()
            }
        )

    }
}