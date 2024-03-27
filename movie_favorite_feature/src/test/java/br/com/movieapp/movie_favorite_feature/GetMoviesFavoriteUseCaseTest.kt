package br.com.movieapp.movie_favorite_feature

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMoviesFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
    )

    private val getMoviesFavoriteUseCase by lazy {
        GetMoviesFavoriteUseCaseImpl(
            repository = movieFavoriteRepository
        )
    }

    @Test
    fun `must return a list of movies when the call to the repository returns a list of movies`() =
        runTest {

            //Given
            whenever(movieFavoriteRepository.getFavoriteMovies())
                .thenReturn(flowOf(movies))

            //When
            val result = getMoviesFavoriteUseCase.invoke().first()

            //Then
            assertThat(result).isEqualTo(movies)
            assertThat(result.size).isEqualTo(2)
        }

    @Test
    fun `must return a failure when throws an exception `() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.getFavoriteMovies())
            .thenThrow(exception)

        //When
        val result = getMoviesFavoriteUseCase.invoke().toList()

        //Then
        verify(movieFavoriteRepository).getFavoriteMovies()
        assertThat(result).isEmpty()
    }

    @Test
    fun `must return an empty list when the call to the repository returns an empty list`() =
        runTest {

            //Given
            whenever(movieFavoriteRepository.getFavoriteMovies())
                .thenReturn(flowOf(emptyList()))

            //When
            val result = getMoviesFavoriteUseCase.invoke().first()

            //Then
            assertThat(result).isEmpty()
        }
}