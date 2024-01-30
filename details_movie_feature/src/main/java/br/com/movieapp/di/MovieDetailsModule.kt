package br.com.movieapp.di

import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.data.repository.MovieDetailsRepositoryImpl
import br.com.movieapp.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.domain.repository.MovieDetailsRepository
import br.com.movieapp.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource{
        return MovieDetailsRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(dataSource: MovieDetailsRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository)
    }



}