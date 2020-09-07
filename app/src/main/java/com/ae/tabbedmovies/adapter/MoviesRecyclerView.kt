package com.ae.tabbedmovies.adapter

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ae.tabbedmovies.BuildConfig
import com.ae.tabbedmovies.R
import com.ae.tabbedmovies.databinding.ItemBinding
import com.ae.tabbedmovies.dto.ResultsItemEntity


import com.bumptech.glide.Glide


class MoviesRecyclerView(private val context: Context):
    RecyclerView.Adapter<MoviesRecyclerView.ViewHolder>() {
    private var moviesList: ArrayList<ResultsItemEntity> = ArrayList()

    fun addItems(results: List<ResultsItemEntity>) {
        moviesList.addAll(results)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(
            R.layout.item,
            p0, false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(moviesList[i], context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemBinding.bind(itemView)

        fun bind(resultsItem: ResultsItemEntity, context: Context) {
            binding.apply {
                title.text = resultsItem.title
                description.text = resultsItem.overview
                Glide.with(context)
                    .load(BuildConfig.BASE_URL_IMAGES + resultsItem.backdropPath)
                    .into(image)
            }

            binding.cardContainer.setOnClickListener {view ->

            }


        }


    }


}