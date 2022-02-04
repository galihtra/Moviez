package com.galih.moviez.ui.content.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.galih.moviez.data.source.CatalogRepository
import com.galih.moviez.data.source.local.entitiy.MovieEntitiy
import com.galih.moviez.data.source.local.entitiy.TvShowEntity
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val catalogRepository: CatalogRepository): ViewModel() {

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntitiy>> = catalogRepository.getFavoriteMovies()

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = catalogRepository.getFavoriteTvShow()

}