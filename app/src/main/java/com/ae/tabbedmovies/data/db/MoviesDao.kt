package com.ae.tabbedmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ae.tabbedmovies.entity.ResultsItemEntity

@Dao
interface MoviesDao {

    /**
     * Method to get data from DB
     *
     * @return results
     */
    @Query("SELECT * FROM movies")
    fun getMoviesFromDatabase(): LiveData<List<ResultsItemEntity>>

    /**
     * Insert results in DB
     *
     * @param resultsItem
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMoviesToDatabase(resultsItem: List<ResultsItemEntity>)
}