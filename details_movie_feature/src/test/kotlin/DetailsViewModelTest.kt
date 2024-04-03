import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import br.com.movieapp.cmore.network.utils.Constants
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.presentation.MovieDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import core.model.MovieDetailsFactory
import core.model.MovieFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase

    @Mock
    lateinit var addMoviesDetailsUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var deleteMovieDetailsUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    private val movieDetailsFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        )
    )

    private val movie =
        MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val viewModel by lazy {

        MovieDetailsViewModel(
            movieDetailsUseCase = getMovieDetailsUseCase,
            addMovieFavorite = addMoviesDetailsUseCase,
            deleteMovieFavorite = deleteMovieDetailsUseCase,
            isMovieFavorite = isMovieFavoriteUseCase,
            savedStateHandler = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>(Constants.MOVIE_DETAILS_ARGUMENT_KEY)).thenReturn(
                    movie.id
                )
            }
        )
    }

    @Test
    fun `must notify uiState with success when get movies similar and movies details returns success`() =
        runTest {
            //Given
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(
                    (ResultData.Success(flowOf(pagingData) to movieDetailsFactory))
                )

            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Success(true))
            )

            val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()
            val checkedCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedCaptor.firstValue.movieId)

            verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
            assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)

            //Then
            val movieDetails = viewModel.uiState.movieDetails
            assertThat(movieDetails).isEqualTo(movieDetailsFactory)

            val moviesSimilar = viewModel.uiState.results
            assertThat(moviesSimilar.first()).isEqualTo(pagingData)
        }

    @Test
    fun `must notify uiState with failure when get movies details returns an exception`() =
        runTest {
            //Given
            val exception = Exception("ERROR")
            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(
                    (ResultData.Failure(exception))
                )

            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Failure(exception))
            )

            //When
            viewModel.uiState.isLoading

            //Then
            val error = viewModel.uiState.isError
            assertThat(exception.message).isEqualTo(error)
        }

    @Test
    fun `must call delete favorite and notify uiState with filled  favorite icon`() = runTest {
        //Given
        whenever(deleteMovieDetailsUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(Unit)))

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf(ResultData.Success(true)))

        val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
        val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

        //When
        viewModel.onAddFavorite(movie = movie)

        verify(deleteMovieDetailsUseCase).invoke(deleteArgumentCaptor.capture())
        assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

        verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

        val iconColor = viewModel.uiState.iconColor
        assertThat(iconColor).isEqualTo(Color.White)
    }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns true`() =
        runTest {
            //Given
            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(ResultData.Success(true)))

            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(
                    (ResultData.Success(flowOf(pagingData) to movieDetailsFactory))
                )
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(iconColor).isEqualTo(Color.Red)
        }

    @Test
    fun `must notify uiState with bookmark icon filled in if bookmark check returns false`() =
        runTest {
            val exception = Exception()
            //Given
            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf(ResultData.Failure(exception)))


            whenever(getMovieDetailsUseCase.invoke(any()))
                .thenReturn(
                    (ResultData.Success(flowOf(pagingData) to movieDetailsFactory))
                )


            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            //When
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertThat(iconColor).isEqualTo(Color.White)
        }
}