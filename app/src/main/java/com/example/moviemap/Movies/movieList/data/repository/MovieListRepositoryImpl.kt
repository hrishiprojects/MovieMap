package com.example.moviemap.Movies.movieList.data.repository

import com.example.moviemap.Movies.movieList.data.remote.MovieApi
import com.example.moviemap.Movies.movieList.data.local.movie.MovieDatabase

import com.example.moviemap.Movies.movieList.domain.model.Movie
import com.example.moviemap.Movies.movieList.domain.repository.MovieListRepository
import com.example.moviemap.Movies.movieList.util.Resource
import kotlinx.coroutines.flow.Flow

class MovieListRepositoryImpl @ Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabse
)
    :MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieListByCategory(category)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        TODO("Not yet implemented")
    }
}