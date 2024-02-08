package br.com.movieapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.movieapp.persistence.data.entity.MovieEntity


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {

}