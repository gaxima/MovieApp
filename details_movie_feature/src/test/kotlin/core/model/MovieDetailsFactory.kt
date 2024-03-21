package core.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.data.model.MovieDetails

class MovieDetailsFactory {

    fun create(poster: Poster) = when (poster) {

        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Avengers",
                voteAverage = 7.5,
                genres = listOf("Action", "Adventure"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "url",
                releaseDate = "releaseDate",
                duration = 120,
                voteCount = 100,
            )
        }

        Poster.JohnWick -> {
            MovieDetails(
                id = 2,
                title = "John Wick",
                voteAverage = 8.0,
                genres = listOf("Action", "Thriller"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "url",
                releaseDate = "releaseDate",
                duration = 120,
                voteCount = 100,
            )
        }
    }

    sealed class Poster {
        object Avengers : Poster()
        object JohnWick : Poster()
    }
}