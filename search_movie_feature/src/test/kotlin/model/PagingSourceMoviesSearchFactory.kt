package model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.search_movie_feature.data.model.MovieSearch
import br.com.movieapp.search_movie_feature.data.paging.MovieSearchPagingSource

class PagingSourceMoviesSearchFactory {

    fun create(movies: List<MovieSearch>) = object : PagingSource<Int, MovieSearch>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
        }

        override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int = 1
    }
}