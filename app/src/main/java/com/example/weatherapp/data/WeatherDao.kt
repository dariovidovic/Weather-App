package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(forecastResponse: ForecastResponse?)

    @Query("DELETE FROM weather_table WHERE forecastId = :id")
    suspend fun deleteCity(id: Int)

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllCities()

    @Query("UPDATE weather_table SET favStatus = :isFavouriteStatus WHERE forecastId = :id")
    suspend fun setFavStatus(id : Int, isFavouriteStatus: Boolean)

    @Query("UPDATE weather_table SET recentStatus = :isRecentStatus WHERE forecastId = :id")
    suspend fun setRecentStatus(id : Int, isRecentStatus: Boolean)

    @Query("SELECT favStatus FROM weather_table WHERE forecastId = :id")
    suspend fun getFavStatus(id : Int) : Boolean

    @Query("DELETE FROM weather_table WHERE favStatus =1")
    suspend fun deleteFavCities()

    @Query("DELETE FROM weather_table WHERE favStatus =0")
    suspend fun deleteRecentCities()

    @Query("SELECT * FROM weather_table ORDER BY forecastId ASC")
    fun readAllData(): LiveData<List<ForecastResponse?>>

    @Query("SELECT * FROM weather_table WHERE favStatus=1")
    fun getFavourites() : LiveData<List<ForecastResponse?>>



}