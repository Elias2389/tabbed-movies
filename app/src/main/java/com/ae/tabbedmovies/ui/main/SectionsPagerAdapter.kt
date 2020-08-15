package com.ae.tabbedmovies.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ae.tabbedmovies.R
import com.ae.tabbedmovies.ui.popularmovies.view.PopularMoviesFragment
import com.ae.tabbedmovies.ui.topdated.view.TopRatedFragment
import com.ae.tabbedmovies.ui.upcoming.view.UpComingFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2,
        R.string.tab_text_3
)


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> { fragment = PopularMoviesFragment() }
            1 -> { fragment = TopRatedFragment() }
            2 -> { fragment = UpComingFragment() }
        }

        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}