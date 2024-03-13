package br.com.movieapp.core.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import br.com.movieapp.persistence.MovieDataBase
import br.com.movieapp.persistence.data.dao.MovieDao
import br.com.movieapp.persistence.data.entity.MovieEntity
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTestExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("teste_db")
    lateinit var database: MovieDataBase
    private lateinit var movieDao: MovieDao


    @Before
    fun setup() {
        hiltRule.inject()
        movieDao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun test_getMovies_should_return_list_of_movies() = runTest {

        //Given  - Nothing

        //When - Get movies
        val movies = movieDao.getMovies().first()

        //Then - Check if movies is empty
        assertThat(movies.size).isEqualTo(0)
    }

    @Test
    fun test_getMovies_should_return_list_ordered_by_id() = runTest {

        //Given - Insert movies
        val movies1 = listOf(
            MovieEntity(1, "Movie 1", "Overview 1"),
            MovieEntity(4, "Movie 2", "Overview 2"),
            MovieEntity(2, "Movie 3", "Overview 3"),
            MovieEntity(3, "Movie 4", "Overview 4")
        )

        movieDao.insertMovies(movies1)

        //When - Get movies
        val movies = movieDao.getMovies().first()

        //Then - Check if movies is ordered by id
        assertThat(movies[0].movieId).isEqualTo(1)
        assertThat(movies[1].movieId).isEqualTo(2)
        assertThat(movies[2].movieId).isEqualTo(3)
        assertThat(movies[3].movieId).isEqualTo(4)
    }

    @Test
    fun test_getMovieById_should_return_correct_movie_by_id() = runTest {

        //Given - Insert movies
        val movieEntity = MovieEntity(3, "Spiderman", "Overview 4")
        movieDao.insertMovie(movieEntity)
        val movies = movieDao.getMovies().first()
        val movieClick = movies[0]

        //When - Get movie by id
        val movieId = movieDao.getMovieById(movieClick.movieId)

        //Then - Check if movie is not null
        assertThat(movieId?.title).isEqualTo(movieClick.title)
    }

    @Test
    fun test_InsertMovies_should_insert_and_return_list_of_movies() = runTest {

        //Given - Insert movies
        val movies = listOf(
            MovieEntity(1, "Movie 1", "Overview 1"),
            MovieEntity(2, "Movie 2", "Overview 2"),
            MovieEntity(3, "Movie 3", "Overview 3"),
            MovieEntity(4, "Movie 4", "Overview 4")
        )

        movieDao.insertMovies(movies)


        //When - Get movies
        val moviesResult = movieDao.getMovies().first()

        //Then - Check if movies is not empty
        assertThat(movies.size).isEqualTo(moviesResult.size)
        assertThat(moviesResult.containsAll(movies))
    }

    @Test
    fun test_should_insert_movie_success() = runTest {

        //Given - Insert movie
        val movie = MovieEntity(1, "Movie 1", "Overview 1")
        movieDao.insertMovie(movie)

        //When - Get movie by id
        val movieResult = movieDao.getMovieById(movie.movieId)

        //Then - Check if movie is not null
        assertThat(movieResult).isNotNull()
        assertThat(movieResult?.title).isEqualTo(movie.title)
    }

    @Test
    fun test_IsFavorite_should_return_favorite_when_movie_marked_as_favorite() = runTest {

        //Given - Insert movie
        val movie = MovieEntity(1, "Movie 1", "Overview 1")
        movieDao.insertMovie(movie)

        //When - Mark movie as favorite
        val movieFavorite = movieDao.isFavorite(movie.movieId)

        //Then - Check if movie is not null
        assertThat(movieFavorite).isNotNull()
    }

    @Test
    fun test_Isfavorite_should_return_null_when_movie_not_marked_as_favorite() = runTest {

        //Given - Insert movie
        val movie = MovieEntity(1, "Movie 1", "Overview 1")
        movieDao.insertMovie(movie)

        //When - Mark movie as favorite
        val movieFavorite = movieDao.isFavorite(2)

        //Then - Check if movie is null
        assertThat(movieFavorite).isNull()
    }

    @Test
    fun test_delete_movie_should_return_null_when_delete_movie_success() = runTest {

        //Given - Insert movie
        val movie = MovieEntity(1, "Movie 1", "Overview 1")
        movieDao.insertMovie(movie)

        //When - Delete movie
        movieDao.deleteMovie(movie)

        //Then - Check if movie is null
        val movieResult = movieDao.getMovieById(movie.movieId)
        assertThat(movieResult).isNull()
    }

    @Test
    fun test_update_movie_should_return_updated_movie() = runTest {

        //Given - Insert movie
        val movie = MovieEntity(1, "Movie 1", "Overview 1")
        movieDao.insertMovie(movie)

        //When - Update movie
        val movieUpdate = MovieEntity(1, "Movie 2", "Overview 2")
        movieDao.insertMovie(movieUpdate)

        //Then - Check if movie is updated
        val movieResult = movieDao.getMovieById(movie.movieId)
        assertThat(movieResult?.title).isEqualTo(movieUpdate.title)
    }

    @Test
    fun test_update_movie_when_have_a_list_of_movies_should_return_updated_movies() = runTest {

        //Given - Insert movies
        val movies = listOf(
            MovieEntity(1, "Movie 1", "Overview 1"),
            MovieEntity(2, "Movie 2", "Overview 2"),
            MovieEntity(3, "Movie 3", "Overview 3"),
            MovieEntity(4, "Movie 4", "Overview 4")
        )

        movieDao.insertMovies(movies)

        //When - Update movies
        val moviesUpdate = listOf(
            MovieEntity(1, "Spiderman", "Overview 1"),
            MovieEntity(2, "Hulk", "Overview 2"),
            MovieEntity(3, "Ironman", "Overview 3"),
            MovieEntity(4, "Antman", "Overview 4")
        )

        movieDao.insertMovies(moviesUpdate)

        //Then - Check if movies is updated
        val moviesResult = movieDao.getMovies().first()
        assertThat(moviesResult.size).isEqualTo(moviesUpdate.size)
        assertThat(moviesResult[0].title).contains(moviesUpdate[0].title)
        assertThat(moviesResult[1].title).contains(moviesUpdate[1].title)
        assertThat(moviesResult[2].title).contains(moviesUpdate[2].title)
        assertThat(moviesResult[3].title).contains(moviesUpdate[3].title)
    }


}

