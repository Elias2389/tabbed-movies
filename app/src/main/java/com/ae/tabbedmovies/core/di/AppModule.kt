package com.ae.tabbedmovies.core.di

import com.ae.tabbedmovies.BuildConfig
import com.ae.tabbedmovies.common.Constants.Companion.API_KEY
import com.ae.tabbedmovies.data.db.MoviesDao
import com.ae.tabbedmovies.data.service.MoviesServices
import com.ae.tabbedmovies.data.db.AppDatabase
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule: Module = module {

    /**
     * Provide HttpLoggingInterceptor
     */
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * Provide OkHttpClient
     */
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor { chain ->
                val urlRequest: HttpUrl = chain.request().url
                val url: HttpUrl = urlRequest.newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()
                val request: Request = chain.request().newBuilder()
                    .url(url)
                    .build()

                return@addInterceptor chain.proceed(request)
            }
            .build()
    }

    /**
     * Provide Retrofit
     */
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provide MoviesServices
     */
    single<MoviesServices> { get<Retrofit>().create(MoviesServices::class.java) }

    /**
     * Provide MoviesDao
     */
    single<MoviesDao> { AppDatabase.getInstance(get()).moviesDao() }

}