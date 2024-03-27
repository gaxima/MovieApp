import androidx.paging.PagingSource
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.search_movie_feature.data.model.MovieSearch
import br.com.movieapp.search_movie_feature.data.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature.domain.source.MovieSearchRemoteDataSource
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import model.MovieSearchPagingFactory

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchPagingSourceTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val movieSearchFactory = MovieSearchFactory()

    private val movieSearchPagingFactory = MovieSearchPagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            movieSearchRemoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `must return a success load when load is called`() = runTest {
        //Given
        whenever(remoteDataSource.getSearchMovies(query = "", page = 1)).thenReturn(
            movieSearchPagingFactory
        )
        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            movieSearchFactory.create(MovieSearchFactory.Poster.Avengers),
            movieSearchFactory.create(MovieSearchFactory.Poster.JohnWick)
        )
        //Then
        assertThat(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            )
        ).isEqualTo(result)
    }

    @Test
    fun `must return a error load when load is called`() = runTest {
        //Given
        val exception = RuntimeException()

        whenever(remoteDataSource.getSearchMovies(any(), any())).thenThrow(
            exception
        )
        //when
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        //then
        assertThat(PagingSource.LoadResult.Error<Int, MovieSearch>(exception)).isEqualTo(result)
    }


}