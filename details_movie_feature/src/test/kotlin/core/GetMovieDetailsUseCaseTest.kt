package core

import TestDispatcherRule
import androidx.paging.PagingConfig
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.domain.repository.MovieDetailsRepository
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import core.model.MovieDetailsFactory
import core.model.MovieFactory
import core.model.PagingSourceMoviesFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieDetailsUseCaseTest {

    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieDetailRepository: MovieDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailFactory =
        MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(listOf(movieFactory))

    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCaseImpl(movieDetailRepository)
    }

    @Test
    fun `should return success from ResultData when get both requests with `() = runTest {

        // Given
        whenever(movieDetailRepository.getMovieDetails(movieFactory.id)).thenReturn(
            movieDetailFactory
        )
        whenever(movieDetailRepository.getMoviesSimilar(movieFactory.id)).thenReturn(
            pagingSourceFake
        )

        // When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        // Then
        verify(movieDetailRepository).getMovieDetails(movieFactory.id)
        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)
        assertThat(result).isNotNull()
        assertThat(result is ResultData.Success).isTrue()


    }

    @Test
    fun `should return an empty flow if the calls to the getMoviesDetails  method doesnt work well`() =
        runTest {
            //Given
            val exception = RuntimeException()
            whenever(movieDetailRepository.getMovieDetails(movieFactory.id)).thenThrow(exception)

            //When
            val result = getMovieDetailsUseCase.invoke(
                GetMovieDetailsUseCase.Params(
                    movieId = movieFactory.id,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
            )
            //Then
            verify(movieDetailRepository).getMovieDetails(movieFactory.id)

            assertThat(result is ResultData.Failure).isTrue()
        }

    @Test
    fun `should return an empty flow if the calls to the getMoviesSimilar  method doesnt work well`()=
        runTest{
        //Given
        val exception = RuntimeException()
        whenever(movieDetailRepository.getMoviesSimilar(movieFactory.id)).thenThrow(exception)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movieFactory.id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )
        //Then
        verify(movieDetailRepository).getMoviesSimilar(movieFactory.id)

        assertThat(result is ResultData.Failure).isTrue()
    }
}