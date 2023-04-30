package com.example.weatherapp.utils

import androidx.room.TypeConverter
import com.example.weatherapp.data.*
import com.google.gson.Gson

class WeatherConverters {

    @TypeConverter
    fun fromListToJson(value: List<*>?) = Gson().toJson(value)

    @TypeConverter
    fun fromForecastListToJson(value: String) = Gson().fromJson(value, Array<ForecastDay>::class.java)

    @TypeConverter
    fun currentToString(current: Current): String = Gson().toJson(current)

    @TypeConverter
    fun stringToCurrent(string: String): Current = Gson().fromJson(string, Current::class.java)

    @TypeConverter
    fun fromForecastToJson(list: Forecast) : String = Gson().toJson(list)

    @TypeConverter
    fun jsonToForecast(value: String) : Forecast = Gson().fromJson(value, Forecast::class.java)




}