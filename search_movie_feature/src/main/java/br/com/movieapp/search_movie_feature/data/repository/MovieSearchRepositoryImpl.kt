package br.com.movieapp.search_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.search_movie_feature.data.model.MovieSearch
import br.com.movieapp.search_movie_feature.data.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val movieSearchRemoteDataSource: MovieSearchRemoteDataSource
) : MovieSearchRepository {

    override fun getSearchMovies(query: String): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(
            query = query,
            movieSearchRemoteDataSource = movieSearchRemoteDataSource
        )
    }
}