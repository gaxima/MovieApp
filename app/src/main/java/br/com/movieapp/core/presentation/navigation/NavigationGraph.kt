package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.movieapp.cmore.network.utils.Constants
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteScreen
import br.com.movieapp.movie_favorite_feature.presentation.MovieFavoriteViewModel
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularScreen
import br.com.movieapp.movie_popular_feature.presentation.MoviePopularViewModel
import br.com.movieapp.presentation.MovieDetailsScreen
import br.com.movieapp.presentation.MovieDetailsViewModel
import br.com.movieapp.search_movie_feature.MovieSearchEvent
import br.com.movieapp.search_movie_feature.presentation.MovieSearchScreen
import br.com.movieapp.search_movie_feature.presentation.MovieSearchViewModel


@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {

            val viewModel: MoviePopularViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            MoviePopularScreen(
                uiState = uiState,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.MovieDetails.passMovieId(movieId = it))
                }
            )
        }

        composable(BottomNavItem.MovieSearch.route) {

            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch
            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToDetailMovie = {
                    navController.navigate(DetailScreenNav.MovieDetails.passMovieId(movieId = it))
                }
            )
        }


        composable(
            route = DetailScreenNav.MovieDetails.route,
            arguments = listOf(navArgument(Constants.MOVIE_DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
                defaultValue = 0
            }
            )
        ) {

            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            val onAddFavorite = viewModel::onAddFavorite

            MovieDetailsScreen(
                uiState = uiState,
                onAddFavorite = onAddFavorite,
            )
        }

        composable(BottomNavItem.MovieFavorite.route) {
            val viewModel: MovieFavoriteViewModel = hiltViewModel()
            val uiState = viewModel.uiState
            MovieFavoriteScreen(
                uiState = uiState,
                navigateToDetails = {
                    navController.navigate(DetailScreenNav.MovieDetails.passMovieId(movieId = it))
                }
            )
        }
    }

}