package br.com.movieapp.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.persistence.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("teste_db")
    fun providesInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context,
            MovieDataBase::class.java
        ).allowMainThreadQueries().build()

}