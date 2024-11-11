package com.example.moviemap.Movies.movieList.data.local.movie.movie

import androidx.room.Database

@Database(
    entities = [MovieEntity::class],
    version = 1
)




abstract class MovieDatabase {
    abstract val movieDao: MovieDao
}