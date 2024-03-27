package br.com.movieapp.movie_favorite_feature

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DeleteMovieFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val deleteMovieFavoriteUseCase by lazy {
        DeleteMovieFavoriteUseCaseImpl(
            repository = movieFavoriteRepository
        )
    }

    @Test
    fun `must return success when deleting movie calls returns a unit from the repository`() =
        runTest {

            //Given
            whenever(movieFavoriteRepository.delete(movie))
                .thenReturn(Unit)
            //When
            val result = deleteMovieFavoriteUseCase.invoke(
                DeleteMovieFavoriteUseCase.Params(movie = movie)
            ).first()

            //Then
            assertThat(result).isEqualTo(ResultData.Success(Unit))

        }

    @Test
    fun `must return a failure result status when the call to the repository returns an exception`() =
        runTest {
            //given
            val exception = RuntimeException()
            whenever(movieFavoriteRepository.delete(movie))
                .thenThrow(exception)

            //when
            val result = deleteMovieFavoriteUseCase.invoke(
                DeleteMovieFavoriteUseCase.Params(movie = movie)
            ).first()
            //then
            assertThat(result).isEqualTo(ResultData.Failure(exception))
        }




}