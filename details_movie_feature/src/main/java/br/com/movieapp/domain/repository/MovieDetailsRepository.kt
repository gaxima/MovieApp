package br.com.movieapp.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.data.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails

    fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>

}