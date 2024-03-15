import androidx.paging.PagingData
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature.presentation.MovieSearchViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getSearchMoviesUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getSearchMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick),
        )
    )

    @Test
    fun `must validate paging data object value when calling movie search paging data`() = runTest {
        //Given
        whenever(getSearchMoviesUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataMovies)
        )

        //When
        viewModel.fetch("")
        val result = viewModel.uiState.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `test must throw an exception when the calling to the use case returns an exception`() =
        runTest {

            //Given
            val exception = RuntimeException("Error")
            whenever(getSearchMoviesUseCase.invoke(any())).thenThrow(exception)

            //When
            viewModel.fetch("")
            val res = viewModel.uiState.movies.first()

            //Then
            assertThat(res).isNull()
        }
}