package br.com.movieapp.persistence.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.persistence.MovieDataBase
import br.com.movieapp.persistence.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDataBase::class.java,
        "movie_database"
    ).build()

    @Provides
    @Singleton
    fun provideMovieDao(dataBase: MovieDataBase) : MovieDao {
        return dataBase.movieDao()
    }

}