package br.com.movieapp.domain.source

import br.com.movieapp.core.network.data.remote.response.MoviesResponse
import br.com.movieapp.data.model.MovieDetails
import br.com.movieapp.data.paging.MovieSimilarPagingSource

interface MovieDetailsRemoteDataSource {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getMoviesSimilar(movieId: Int, page: Int): MoviesResponse

    fun getSimilarMoviesPagingSource(movieId: Int): MovieSimilarPagingSource
}