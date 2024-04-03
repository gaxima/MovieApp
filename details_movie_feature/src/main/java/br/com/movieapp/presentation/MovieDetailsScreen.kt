package br.com.movieapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.presentation.components.MovieDetailsContent
import br.com.movieapp.presentation.state.MovieDetailsState
import br.com.movieapp.ui.components.LoadingItem
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsState,
    onAddFavorite: (Movie) -> Unit,
) {

    val pagingMoviesSimilar = uiState.results.collectAsLazyPagingItems()

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
            if (pagingMoviesSimilar.loadState.refresh is LoadState.Loading && pagingMoviesSimilar.itemCount == 0) {
                LoadingItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(black)
                )
            } else {
                MovieDetailsContent(
                    movieDetails = uiState.movieDetails,
                    pagingMoviesSimilar = pagingMoviesSimilar,
                    isLoading = uiState.isLoading,
                    isError = uiState.isError,
                    iconColor = uiState.iconColor,
                    onAddFavorite = {
                        onAddFavorite(it)
                    }
                )
            }
        }
    )
}