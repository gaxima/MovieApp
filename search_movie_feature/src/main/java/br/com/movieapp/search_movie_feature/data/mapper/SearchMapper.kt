package br.com.movieapp.search_movie_feature.data.mapper

import br.com.movieapp.core.data.remote.model.SearchResult
import br.com.movieapp.core.utils.toPostUrl
import br.com.movieapp.search_movie_feature.data.model.MovieSearch

fun List<SearchResult>.toMovieSearch() = map { it ->
    MovieSearch(
        id = it.id,
        voteAverage = it.vote_average,
        imageUrl = it.poster_path.toPostUrl()
    )
}