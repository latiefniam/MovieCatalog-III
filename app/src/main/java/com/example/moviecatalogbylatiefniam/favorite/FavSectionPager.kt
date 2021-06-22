@file:Suppress("DEPRECATION")

package com.example.moviecatalogbylatiefniam.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogbylatiefniam.R
import com.example.moviecatalogbylatiefniam.favorite.movie.FavMovFragment
import com.example.moviecatalogbylatiefniam.favorite.tv.FavTvFragment


class FavSectionPager(private val mContext: Context, fm: FragmentManager):
FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object{
        @StringRes
        private val TITLES = intArrayOf(R.string.movie, R.string.tvshow)
    }

    override fun getCount(): Int = TITLES.size

    override fun getItem(position: Int): Fragment =
        when (position){
            0 -> FavMovFragment()
            1 -> FavTvFragment()
            else -> Fragment()
        }
    override fun getPageTitle(position: Int): CharSequence =
        mContext.resources.getString(TITLES[position])
}