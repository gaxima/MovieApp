package br.com.movieapp

import androidx.paging.PagingData
import br.com.movieapp.movie_popular_feature.domain.usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.movie_popular_feature.presentation.state.MoviePopularState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviePopularViewModelTest {

    private val getPopularMoviesUseCase: GetPopularMoviesUseCase = mockk()

    private lateinit var viewModel: MoviePopularViewModel

    @Before
    fun setUp() {
        viewModel = MoviePopularViewModel(getPopularMoviesUseCase)
    }
}
