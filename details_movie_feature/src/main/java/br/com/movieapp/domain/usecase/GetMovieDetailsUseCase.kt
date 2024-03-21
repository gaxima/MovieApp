package br.com.movieapp.domain.usecase


import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.commons.model.Movie
import br.com.movieapp.commons.utils.ResultData
import br.com.movieapp.data.model.MovieDetails
import br.com.movieapp.domain.repository.MovieDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>
    data class Params(val movieId: Int)
}

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val moviesSimilar = repository.getMoviesSimilar(
                    movieId = params.movieId,
                    pagingConfig = PagingConfig(
                        pageSize = 20,
                        initialLoadSize = 20
                    )
                )
                emit(ResultData.Success(moviesSimilar to movieDetails))
            } catch (exception: IOException) {
                emit(ResultData.Failure(exception))
            } catch (exception: Exception) {
                emit(ResultData.Failure(exception))
            }
        }.flowOn(Dispatchers.IO)
    }
}