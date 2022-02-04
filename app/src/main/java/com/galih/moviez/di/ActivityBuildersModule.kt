package com.galih.moviez.di

import com.galih.moviez.di.home.HomeFragmentBuildersModule
import com.galih.moviez.ui.detail.DetailActivity
import com.galih.moviez.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

}