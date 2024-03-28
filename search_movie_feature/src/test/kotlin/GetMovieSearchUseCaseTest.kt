import androidx.paging.PagingConfig
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.search_movie_feature.domain.repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature.domain.usecase.GetMovieSearchUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import model.PagingSourceMoviesSearchFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(
    MockitoJUnitRunner::class
)
class GetMovieSearchUseCaseTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieSearchRepository: MovieSearchRepository

    private val movieSearchFactory =
        MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesSearchFactory().create(
        listOf(movieSearchFactory)
    )

    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImpl(movieSearchRepository)
    }

    // Add test cases here
    @Test
    fun `should validate flow paging data when invoke from use case is called`() = runTest {
        // Given
        whenever(movieSearchRepository.getSearchMovies(""))
            .thenReturn(pagingSourceFake)
        // When
        val result = getMovieSearchUseCase.invoke(
            params = GetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        // Then
        assertThat(result).isNotNull()
    }


    @Test
    fun `must return an empty flow when an error occurs when invoking method`() = runTest {

        // Given
        val exception = RuntimeException()
        whenever(movieSearchRepository.getSearchMovies(""))
            .thenThrow(exception)
        
        // When
        val result = getMovieSearchUseCase.invoke(
            params = GetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).toList()

        // Then
        verify(movieSearchRepository).getSearchMovies("")
        assertThat(result).isEmpty()
    }
}