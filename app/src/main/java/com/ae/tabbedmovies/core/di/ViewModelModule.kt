package com.ae.tabbedmovies.core.di

import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.ui.popularmovies.viewmodel.PopularMoviesViewModel
import com.ae.tabbedmovies.ui.topdated.viewmodel.TopRatedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule: Module = module {

    /**
     * Provide PopularMoviesViewModel
     */
    viewModel<PopularMoviesViewModel> { PopularMoviesViewModel(get<MoviesServices>()) }

    /**
     * Provide TopRatedMoviesViewModel
     */
    viewModel<TopRatedViewModel> { TopRatedViewModel(get<MoviesServices>()) }
}