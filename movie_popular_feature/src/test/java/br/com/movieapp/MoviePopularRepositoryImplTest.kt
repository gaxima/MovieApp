package br.com.movieapp

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.core.data.remote.model.MoviesResult
import br.com.movieapp.core.network.data.remote.response.MoviesResponse
import br.com.movieapp.core.network.model.Movie
import br.com.movieapp.movie_popular_feature.data.paging.MoviePagingSource
import br.com.movieapp.movie_popular_feature.data.repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popular_feature.domain.source.MoviePopularRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class MoviePopularRepositoryImplTest {

    private val remoteDataSource: MoviePopularRemoteDataSource = mockk()

    private lateinit var moviePagingSource: MoviePagingSource

    private lateinit var repository: MoviePopularRepositoryImpl

    private val pagingConfig: PagingConfig = mockk()

    @Before
    fun setUp() {
        moviePagingSource = MoviePagingSource(remoteDataSource)
        repository = MoviePopularRepositoryImpl(remoteDataSource)

        coEvery {
            remoteDataSource.getPopularMoviesPagingSource()
        }.returns(moviePagingSource)

        coEvery { remoteDataSource.getPopularMovies(any()) } returns MoviesResponse(
            page = 1,
            results = listOf(
                MoviesResult(
                    id = 1,
                    title = "filme",
                    adult = true,
                    backdrop_path = "backdrop_path",
                    genre_ids = listOf(1, 2),
                    original_language = "pt",
                    original_title = "filme",
                    overview = "filme",
                    popularity = 1.0,
                    poster_path = "poster_path",
                    release_date = "2021-01-01",
                    video = true,
                    vote_average = 1.0,
                    vote_count = 1
                ),
                MoviesResult(
                    id = 2,
                    title = "filme2",
                    adult = true,
                    backdrop_path = "backdrop_path",
                    genre_ids = listOf(1, 2),
                    original_language = "pt",
                    original_title = "filme",
                    overview = "filme",
                    popularity = 1.0,
                    poster_path = "poster_path",
                    release_date = "2021-01-01",
                    video = true,
                    vote_average = 1.0,
                    vote_count = 1
                )
            ),
            total_pages = 1,
            total_results = 2
        )
    }

    @Test
    fun `getPopularMovies returns expected result`() = runTest {
        val expected = PagingData.from(listOf(Movie(1, "filme"), Movie(2, "filme2")))


        coEvery {
            repository.getPopularMovies(pagingConfig)
        }.returns(flowOf(expected))

        val result = repository.getPopularMovies(pagingConfig).first()

        assertEquals(expected, result)
    }

    @Test
    fun `getPopularMovies returns empty result when no data`() = runTest {
        val expected = PagingData.empty<Movie>()


        coEvery {
            remoteDataSource.getPopularMoviesPagingSource()
        }.returns(moviePagingSource)


        val result = repository.getPopularMovies(pagingConfig).first()

        assertEquals(expected, result)
    }
}