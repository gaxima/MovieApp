package br.com.movieapp.movie_popular_feature.domain.source

import br.com.movieapp.core.data.remote.response.MoviesResponse
import br.com.movieapp.movie_popular_feature.data.paging.MoviePagingSource

interface MoviePopularRemoteDataSource {

    fun getPopularMoviesPagingSource() : MoviePagingSource

    suspend fun getPopularMovies(page: Int) : MoviesResponse
}