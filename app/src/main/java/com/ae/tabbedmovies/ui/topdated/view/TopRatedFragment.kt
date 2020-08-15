package com.ae.tabbedmovies.ui.topdated.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ae.tabbedmovies.R

/**
 * A simple [Fragment] subclass.
 * Use the [TopRatedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopRatedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

}