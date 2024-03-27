import androidx.paging.PagingSource
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.data.paging.MovieSimilarPagingSource
import br.com.movieapp.domain.source.MovieDetailsRemoteDataSource
import com.nhaarman.mockitokotlin2.whenever
import core.model.MovieFactory
import core.model.MoviePagingFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import org.junit.Test

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSimilarPagingTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieDetailsRemoteDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val moviesSimilarPagingSource by lazy {
        MovieSimilarPagingSource(
            movieId = 1,
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `must return a success load when load is called`() = runTest {
        //given
        whenever(remoteDataSource.getMoviesSimilar(1, 1))
            .thenReturn(moviePagingFactory)


        //when
        val result = moviesSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        val resultExpected = PagingSource.LoadResult.Page(
            data = moviePagingFactory.movies,
            prevKey = null,
            nextKey = null
        )

        //then
        assertThat(resultExpected).isEqualTo(result)
    }

    @Test
    fun `must throw an exception when load errors `() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getMoviesSimilar(any(), any()))
            .thenThrow(exception)

        //When
        val result = moviesSimilarPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )

        //Then
        assertThat(PagingSource.LoadResult.Error<Int, Movie>(exception)).isEqualTo(result)
    }
}