package br.com.movieapp.persistence.data.dao

import androidx.compose.ui.input.key.Key.Companion.I
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.movieapp.persistence.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movies  ORDER BY movieId")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    suspend fun getMovieById(movieId: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM Movies WHERE movieId = :movieId")
    suspend fun isFavorite(movieId: Int): MovieEntity?

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

}