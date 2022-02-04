package com.galih.moviez.data.source.remote.api

import com.galih.moviez.BuildConfig
import com.galih.moviez.data.source.remote.response.ListResponse
import com.galih.moviez.data.source.remote.response.MovieResponse
import com.galih.moviez.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY")
    fun getMovies(): Call<ListResponse<MovieResponse>>

    @GET("tv/airing_today?api_key=$API_KEY")
    fun getTvShow(): Call<ListResponse<TvShowResponse>>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = BuildConfig.DB_API_KEY
    }

}