package com.ae.tabbedmovies.ui.popularmovies.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.tabbedmovies.adapter.MoviesRecyclerView
import com.ae.tabbedmovies.databinding.FragmentPopularMoviesBinding
import com.ae.tabbedmovies.data.networkboundresource.Status
import com.ae.tabbedmovies.ui.popularmovies.viewmodel.PopularMoviesViewModel
import org.koin.android.ext.android.inject

/**
 * A placeholder fragment containing a simple view.
 */
class PopularMoviesFragment : Fragment() {

    private val binding get() = popularMoviesBinding!!
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: MoviesRecyclerView
    private var popularMoviesBinding: FragmentPopularMoviesBinding? = null

    private val popularMoviesViewModel by inject<PopularMoviesViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        popularMoviesBinding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        popularMoviesViewModel.popularMovies.observe(viewLifecycleOwner, Observer { resourse ->
            when(resourse.status) {
                Status.LOADING -> {
                    Log.i("loading", "Loading")
                }
                Status.SUCCESS -> {
                    resourse?.data?.results?.let {
                        adapter.addItems(it)
                        adapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {}
            }

        })
    }

    private fun setupAdapter() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        popularMoviesBinding?.recyclerItems?.layoutManager = layoutManager
        adapter = MoviesRecyclerView(requireContext())
        popularMoviesBinding?.recyclerItems?.adapter = adapter
    }



}