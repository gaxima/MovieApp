package br.com.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.core.network.model.Movie
import br.com.movieapp.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_popular_feature.data.mapper.toMovie
import okio.IOException
import retrofit2.HttpException

class MovieSimilarPagingSource(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val movieId: Int
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getMoviesSimilar( page = pageNumber, movieId = movieId)

            val movies = response.results

            LoadResult.Page(
                data = movies.toMovie(),
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }


    companion object {
        private const val LIMIT = 20
    }
}