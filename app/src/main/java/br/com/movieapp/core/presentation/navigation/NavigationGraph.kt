package br.com.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MoviePopular.route
    ) {
        composable(BottomNavItem.MoviePopular.route) {
            //MoviePopularScreen()
        }
        composable(BottomNavItem.MovieSearch.route) {
            //MovieSearchScreen()
        }
        composable(BottomNavItem.MovieFavorite.route) {
            //MovieFavoriteScreen()
        }
    }

}