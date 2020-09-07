package com.ae.tabbedmovies.ui.topdated.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.tabbedmovies.adapter.MoviesRecyclerView
import com.ae.tabbedmovies.databinding.FragmentTopRatedBinding
import com.ae.tabbedmovies.data.networkboundresource.Status
import com.ae.tabbedmovies.ui.topdated.viewmodel.TopRatedViewModel
import org.koin.android.ext.android.inject

class TopRatedFragment : Fragment() {

    private val binding get() = topRatedMoviesBinding!!
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: MoviesRecyclerView
    private var topRatedMoviesBinding: FragmentTopRatedBinding? = null
    private val topRatedViewModel by inject<TopRatedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topRatedMoviesBinding = FragmentTopRatedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        getPopularMovies()
    }


    private fun getPopularMovies() {
        topRatedViewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { resourse ->
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
        topRatedMoviesBinding?.recyclerItems?.layoutManager = layoutManager
        adapter = MoviesRecyclerView(requireContext())
        topRatedMoviesBinding?.recyclerItems?.adapter = adapter
    }

}