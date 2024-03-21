package br.com.movieapp.model

import br.com.movieapp.commons.model.Movie

class MovieFactory {

    fun create(poster: Poster) = when (poster) {

        Poster.Avengers -> Movie(
            id = 1,
            title = "Avengers",
            voteAverage = 7.5,
            imageUrl = "imageUrl"
        )

        Poster.JohnWick -> Movie(
            id = 2,
            title = "John Wick",
            voteAverage = 8.0,
            imageUrl = "imageUrl"
        )
    }

    sealed class Poster {
        object Avengers : Poster()
        object JohnWick : Poster()
    }
}