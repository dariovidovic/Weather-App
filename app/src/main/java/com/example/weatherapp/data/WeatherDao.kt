package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCity(forecastResponse: ForecastResponse?)

    @Query("SELECT * FROM weather_table ORDER BY forecastId ASC")
    fun readAllData(): LiveData<List<ForecastResponse?>>




}