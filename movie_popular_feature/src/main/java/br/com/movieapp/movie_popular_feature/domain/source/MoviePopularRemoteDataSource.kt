package br.com.movieapp.movie_popular_feature.domain.source

import br.com.movieapp.core.network.data.remote.response.MoviePaging
import br.com.movieapp.core.network.data.remote.response.MoviesResponse
import br.com.movieapp.movie_popular_feature.data.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource() : MoviePagingSource

    suspend fun getPopularMovies(page: Int) : MoviePaging
}