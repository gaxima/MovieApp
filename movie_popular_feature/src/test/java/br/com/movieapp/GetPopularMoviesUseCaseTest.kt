package br.com.movieapp

import androidx.paging.PagingConfig
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.core.domain.model.PagingSourceMoviesFactory
import br.com.movieapp.movie_popular_feature.domain.repository.MoviePopularRepository
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var moviePopularRepository: MoviePopularRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.JohnWick)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(
        listOf(movie)
    )

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(moviePopularRepository)
    }

    @Test
    fun `should validate flow pagingData when invoke usecase is called`() = runTest {
        // Given
        whenever(moviePopularRepository.getPopularMovies())
            .thenReturn(pagingSourceFake)
        // When
        val result = getPopularMoviesUseCase.invoke(
            params = GetPopularMoviesUseCase.Params(
                pageConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()
        // Then
        verify(moviePopularRepository).getPopularMovies()
        assertThat(result).isNotNull()
    }

    @Test
    fun `should emit an empty flow when an exception is thrown when the calling invoke method`() =
        runTest {
            //Given
            val exception = java.lang.RuntimeException()
            whenever(moviePopularRepository.getPopularMovies())
                .thenThrow(exception)
            //When
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.Params(
                    pageConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )

            val resultList = result.toList()

            //Then
            verify(moviePopularRepository).getPopularMovies()
            assertThat(resultList).isEmpty()
        }
}