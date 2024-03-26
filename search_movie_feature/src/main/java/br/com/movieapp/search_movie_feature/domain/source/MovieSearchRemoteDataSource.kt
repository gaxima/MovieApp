package br.com.movieapp.search_movie_feature.domain.source

import br.com.movieapp.search_movie_feature.data.model.MovieSearchPaging
import br.com.movieapp.search_movie_feature.data.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {

    fun getSearchMoviesPagingSource(query: String): MovieSearchPagingSource

    suspend fun getSearchMovies(query: String, page: Int): MovieSearchPaging
}