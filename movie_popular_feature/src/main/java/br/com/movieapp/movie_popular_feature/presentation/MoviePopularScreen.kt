package br.com.movieapp.movie_popular_feature.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.core.utils.UtilsFunctions
import br.com.movieapp.movie_popular_feature.presentation.components.MovieContent
import br.com.movieapp.movie_popular_feature.presentation.state.MoviePopularState
import br.com.movieapp.ui.components.MovieAppBar


@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = br.com.movieapp.ui.R.string.popular_movies)
        }, content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    UtilsFunctions.logInfo("MoviePopularScreen", "movieId: $movieId")
                    navigateToDetailMovie(movieId)
                }
            )
        }
    )


}