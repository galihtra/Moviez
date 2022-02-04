package com.galih.moviez.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.galih.moviez.ui.content.favorite.movie.FavoriteMovieFragment
import com.galih.moviez.ui.content.favorite.tvshow.FavoriteTvShowFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            else -> FavoriteTvShowFragment()
        }
}