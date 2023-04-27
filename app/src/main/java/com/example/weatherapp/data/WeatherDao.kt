package com.example.weatherapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCity(location: Location)

    /*@Query("SELECT * FROM weather_table ORDER BY name ASC")
    fun readAllData() : LiveData<ForecastResponse?>*/

}