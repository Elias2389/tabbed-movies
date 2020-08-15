package com.ae.tabbedmovies.ui.upcoming.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ae.tabbedmovies.R

/**
 * A simple [Fragment] subclass.
 * Use the [UpComingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpComingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_coming, container, false)
    }

}