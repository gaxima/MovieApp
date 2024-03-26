package model

import br.com.movieapp.core.domain.model.MovieSearchFactory
import br.com.movieapp.search_movie_feature.data.model.MovieSearch
import br.com.movieapp.search_movie_feature.data.model.MovieSearchPaging

class MovieSearchPagingFactory {

    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 7.5,
                imageUrl = "imageUrl"
            ),
            MovieSearch(
                id = 2,
                voteAverage = 8.0,
                imageUrl = "imageUrl"
            )
        )
    )
}