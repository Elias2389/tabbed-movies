package com.ae.tabbedmovies.ui.upcoming.view

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
import com.ae.tabbedmovies.databinding.FragmentUpComingBinding
import com.ae.tabbedmovies.data.networkboundresource.Status
import com.ae.tabbedmovies.ui.upcoming.viewmodel.UpComingViewModel
import org.koin.android.ext.android.inject


class UpComingFragment : Fragment() {

    private val binding get() = upComingBinding!!
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: MoviesRecyclerView
    private var upComingBinding: FragmentUpComingBinding? = null
    private val upComingViewModel by inject<UpComingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        upComingBinding = FragmentUpComingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        getPopularMovies()
    }

    private fun getPopularMovies() {
        upComingViewModel.upcoming.observe(viewLifecycleOwner, Observer { resourse ->
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
        upComingBinding?.recyclerItems?.layoutManager = layoutManager
        adapter = MoviesRecyclerView(requireContext())
        upComingBinding?.recyclerItems?.adapter = adapter
    }


}