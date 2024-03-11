import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.ui.components.LoadingItem
import br.com.movieapp.ui.components.MovieItem

@Composable
fun MovieDetailsSimilar(
    pagingMovieSimilar: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier
) {
    when {
        pagingMovieSimilar.loadState.refresh is LoadState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LoadingItem(modifier = Modifier.align(Alignment.Center))
            }
        }

        else -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Center,
                modifier = modifier
            ) {
                items(pagingMovieSimilar.itemCount) { index ->
                    val movie = pagingMovieSimilar[index]
                    movie?.let {
                        MovieItem(
                            voteAverage = it.voteAverage,
                            imageUrl = it.imageUrl,
                            id = it.id,
                            onClick = {

                            }
                        )
                    }
                }
            }
        }
    }
}