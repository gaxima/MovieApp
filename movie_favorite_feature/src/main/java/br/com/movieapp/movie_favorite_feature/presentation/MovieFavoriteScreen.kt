package br.com.movieapp.movie_favorite_feature.presentation

import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.movie_favorite_feature.presentation.components.MovieFavoriteContent
import br.com.movieapp.movie_favorite_feature.presentation.states.MovieFavoriteState
import br.com.movieapp.ui.components.MovieAppBar

@Composable
fun MovieFavoriteScreen(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    navigateToDetails: (Int) -> Unit
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            MovieAppBar(title = br.com.movieapp.ui.R.string.favorite_movies)
        },
        content = { paddingValues ->
            MovieFavoriteContent(
                modifier = Modifier,
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToDetails(movieId)
                }
            )
        }
    )
}

@Preview
@Composable
fun MovieFavoriteScreenPreview() {
    MovieFavoriteScreen(
        movies = emptyList()
    ) {}

}