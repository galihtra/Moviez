package com.galih.moviez.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.galih.moviez.data.source.local.entitiy.MovieEntitiy
import com.galih.moviez.data.source.local.entitiy.TvShowEntity
import com.galih.moviez.vo.Resource

interface CatalogDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntitiy>>>

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntitiy>>

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntitiy>

    fun getDetailTvShow(tvshowId: Int): LiveData<TvShowEntity>

    fun setFavoriteMovie(movie: MovieEntitiy)

    fun setFavoriteTvShow(tvshow: TvShowEntity)

}