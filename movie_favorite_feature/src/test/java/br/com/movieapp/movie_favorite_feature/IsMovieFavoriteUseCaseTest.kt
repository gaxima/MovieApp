package br.com.movieapp.movie_favorite_feature

import br.com.movieapp.TestDispatcherRule
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.model.MovieFactory
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class IsMovieFavoriteUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val isMovieFavoriteUseCase by lazy {
        IsMovieFavoriteUseCaseImpl(
            repository = movieFavoriteRepository
        )
    }

    @Test
    fun `must return true when the call to the repository returns true`() = runTest {
        //Given
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(true)

        //When
        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movieId = movie.id)
        ).first()


        //Then
        assertThat(result).isEqualTo(ResultData.Success(true))
    }

    @Test
    fun `must return false when the call to the repository returns false`() = runTest {
        //Given
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenReturn(false)

        //When
        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movieId = movie.id)
        ).first()


        //Then
        assertThat(result).isEqualTo(ResultData.Success(false))
    }

    @Test
    fun `must return a failure when throws an exception `() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.isFavorite(any()))
            .thenThrow(exception)

        //When
        val result = isMovieFavoriteUseCase.invoke(
            params = IsMovieFavoriteUseCase.Params(movieId = movie.id)
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }

}