package br.com.movieapp

import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import br.com.movieapp.core.domain.model.MovieFactory
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviePopularViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
        )
    )

    @Test
    fun `must validate paging data object values when calling paging data from movies`() = runTest {
        //Given
        whenever(getPopularMoviesUseCase.invoke()).thenReturn(
            flowOf(fakePagingDataMovies)
        )

        //When
        val result = viewModel.uiState.movies.first()


        //Then
        assertThat(result).isNotNull()

    }

    @Test(expected = RuntimeException::class)
    fun `must return a exception when the call to the use case returns a exception`() = runTest {
        //Given
        val exception = Exception("Error")
        whenever(getPopularMoviesUseCase.invoke()).thenThrow(exception)

        //When
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNull()
    }


}
