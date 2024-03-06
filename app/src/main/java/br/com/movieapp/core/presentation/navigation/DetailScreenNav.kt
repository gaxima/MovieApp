package br.com.movieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import br.com.movieapp.cmore.network.utils.Constants
import br.com.movieapp.cmore.network.utils.Constants.MOVIE_DETAILS_ARGUMENT_KEY

sealed class DetailScreenNav(val route: String) {
    object MovieDetails : DetailScreenNav(
        route = "movie_details_destination?${Constants.MOVIE_DETAILS_ARGUMENT_KEY}=" +
                "{${Constants.MOVIE_DETAILS_ARGUMENT_KEY}}"

    ) {
        fun passMovieId(movieId: Int) =
            "movie_details_destination?${Constants.MOVIE_DETAILS_ARGUMENT_KEY}=$movieId"
    }
}