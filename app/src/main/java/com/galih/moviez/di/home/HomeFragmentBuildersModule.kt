package com.galih.moviez.di.home

import com.galih.moviez.ui.content.favorite.FavoriteFragment
import com.galih.moviez.ui.content.favorite.movie.FavoriteMovieFragment
import com.galih.moviez.ui.content.favorite.tvshow.FavoriteTvShowFragment
import com.galih.moviez.ui.content.movie.MovieFragment
import com.galih.moviez.ui.content.tvshow.TvShowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributeMovieFragment(): MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment(): TvShowFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMovieFragment(): FavoriteMovieFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowFragment(): FavoriteTvShowFragment

}