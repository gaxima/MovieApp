package br.com.movieapp.presentation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.presentation.components.MovieDetailsContent
import br.com.movieapp.presentation.state.MovieDetailsState
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    id: Int?,
    uiState: MovieDetailsState,
    getMovieDetail: (MovieDetailsEvent.GetMovieDetails) -> Unit,
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        if (id != null) {
            getMovieDetail(MovieDetailsEvent.GetMovieDetails(id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = br.com.movieapp.ui.R.string.detail_movie),
                        color = white
                    )
                },
                backgroundColor = black
            )
        },
        content = {
            MovieDetailsContent(
                movieDetails = uiState.movieDetails,
                pagingMoviesSimilar = pagingMoviesSimilar,
                isLoading = uiState.isLoading,
                isError = uiState.isError,
                iconColor = uiState.iconColor,
                onAddFavorite = {

                }
            )
        }
    )

}