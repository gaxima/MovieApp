package br.com.movieapp.search_movie_feature.data.source

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.search_movie_feature.data.mapper.toMovieSearch
import br.com.movieapp.search_movie_feature.data.model.MovieSearchPaging
import br.com.movieapp.search_movie_feature.data.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteDataSource {

    override fun getSearchMoviesPagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query, this)
    }

    override suspend fun getSearchMovies(query: String, page: Int): MovieSearchPaging {

        val response = service.searchMovies(query = query, page = page)

        return MovieSearchPaging(
            page = response.page,
            totalPages = response.total_pages,
            totalResults = response.total_results,
            movies = response.results.map { it.toMovieSearch() }
        )
    }
}