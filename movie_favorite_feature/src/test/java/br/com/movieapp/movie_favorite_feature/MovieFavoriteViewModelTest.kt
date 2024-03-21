package br.com.movieapp.movie_favorite_feature

import androidx.paging.PagingData
import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import com.google.common.truth.Truth.assertThat
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMoviesFavoriteUseCase)
    }

    private val moviesFavorite =
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
        )


    @Test
    fun `must validate data object values when calling list of favorites`() = runTest {
        // Given
        whenever(getMoviesFavoriteUseCase.invoke()).thenReturn(
            flowOf(moviesFavorite)
        )
        // When
        val result = viewModel.uiState.movies.first()
        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(moviesFavorite[0])
    }
}