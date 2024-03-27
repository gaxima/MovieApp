package core.model

import br.com.movieapp.core.network.data.remote.response.MoviePaging


class MoviePagingFactory {

    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
        )
    )
}