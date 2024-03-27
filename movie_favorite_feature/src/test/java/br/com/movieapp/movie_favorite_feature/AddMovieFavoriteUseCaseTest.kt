package br.com.movieapp.movie_favorite_feature

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
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
class AddMovieFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(
            repository = movieFavoriteRepository
        )
    }

    @Test
    fun `must return success when the call to the repository returns a success unit`() = runTest {
        //given
        whenever(movieFavoriteRepository.insert(movie))
            .thenReturn(Unit)

        //when
        val result = addMovieFavoriteUseCase.invoke(
            AddMovieFavoriteUseCase.Params(movie = movie)
        ).first()

        //then
        assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `must return failure when the call to the repository returns a failure`() = runTest {

        //given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.insert(movie))
            .thenThrow(exception)

        //when
        val result = addMovieFavoriteUseCase.invoke(
            AddMovieFavoriteUseCase.Params(movie = movie)
        ).first()
        //then
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }
}