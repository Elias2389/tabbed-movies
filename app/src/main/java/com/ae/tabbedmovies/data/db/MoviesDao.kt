package com.ae.tabbedmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ae.tabbedmovies.dto.ResultsItem

@Dao
interface MoviesDao {

    /**
     * Method to get data from DB
     *
     * @return results
     */
    @Query("SELECT * FROM movies")
    fun getMoviesFromDatabase(): LiveData<List<ResultsItem>>

    /**
     * Insert results in DB
     *
     * @param resultsItem
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesToDatabase(resultsItem: List<ResultsItem>)
}