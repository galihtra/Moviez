package com.galih.moviez.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.galih.moviez.data.source.CatalogRepository
import com.galih.moviez.data.source.local.entitiy.MovieEntitiy
import com.galih.moviez.data.source.local.entitiy.TvShowEntity
import com.galih.moviez.vo.Resource
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val catalogRepository: CatalogRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntitiy>>> = catalogRepository.getMovies()

    fun getTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogRepository.getTvShow()

}