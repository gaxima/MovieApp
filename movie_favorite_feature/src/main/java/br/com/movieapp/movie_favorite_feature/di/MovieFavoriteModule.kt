package br.com.movieapp.movie_favorite_feature.di

import br.com.movieapp.movie_favorite_feature.data.repository.MovieFavoriteRepositoryImpl
import br.com.movieapp.movie_favorite_feature.data.source.MovieFavoriteLocalDataSourceImpl
import br.com.movieapp.movie_favorite_feature.domain.repository.MovieFavoriteRepository
import br.com.movieapp.movie_favorite_feature.domain.source.MovieFavoriteLocalDataSource
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.AddMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.DeleteMovieFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.GetMoviesFavoriteUseCaseImpl
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_feature.domain.usecase.IsMovieFavoriteUseCaseImpl
import br.com.movieapp.persistence.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieFavoriteModule {

    @Provides
    @Singleton
    fun provideMovieFavoriteLocalDataSource(dao: MovieDao): MovieFavoriteLocalDataSource {
        return MovieFavoriteLocalDataSourceImpl(dao = dao)
    }

    @Singleton
    @Provides
    fun provideMovieFavoriteRepository(localDataSource: MovieFavoriteLocalDataSource): MovieFavoriteRepository {
        return MovieFavoriteRepositoryImpl(localDataSource = localDataSource)
    }

    @Singleton
    @Provides
    fun provideAddMovieFavoriteUseCase(repository: MovieFavoriteRepository): AddMovieFavoriteUseCase {
        return AddMovieFavoriteUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideDeleteMovieFavoriteUseCase(repository: MovieFavoriteRepository): DeleteMovieFavoriteUseCase {
        return DeleteMovieFavoriteUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideGetMoviesFavoriteUseCase(repository: MovieFavoriteRepository): GetMoviesFavoriteUseCase {
        return GetMoviesFavoriteUseCaseImpl(repository = repository)
    }

    @Singleton
    @Provides
    fun provideIsMovieFavoriteUseCase(repository: MovieFavoriteRepository): IsMovieFavoriteUseCase {
        return IsMovieFavoriteUseCaseImpl(repository = repository)
    }

}