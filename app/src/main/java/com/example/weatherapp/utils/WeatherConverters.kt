package com.example.weatherapp.utils

import androidx.room.TypeConverter
import com.example.weatherapp.data.*
import com.google.gson.Gson

class WeatherConverters {

    @TypeConverter
    fun fromForecastToJson(list : Forecast) : String = Gson().toJson(list)

    @TypeConverter
    fun jsonToForecast(value: String) : Forecast = Gson().fromJson(value, Forecast::class.java)

    @TypeConverter
    fun locationToString(location: Location): String = Gson().toJson(location)

    @TypeConverter
    fun stringToLocation(string: String): Location = Gson().fromJson(string, Location::class.java)

    @TypeConverter
    fun currentToString(current: Current): String = Gson().toJson(current)

    @TypeConverter
    fun stringToCurrent(string: String): Current = Gson().fromJson(string, Current::class.java)

    @TypeConverter
    fun forecastDayToString(forecastday: ForecastDay?) : String = Gson().toJson(forecastday)

    @TypeConverter
    fun stringToForecastDay(string: String) : ForecastDay? = Gson().fromJson(string, ForecastDay::class.java)

    @TypeConverter
    fun dayToString(day: Day?): String = Gson().toJson(day)

    @TypeConverter
    fun stringToDay(string: String): Day? = Gson().fromJson(string, Day::class.java)

    @TypeConverter
    fun hourToString(hour: Hour?): String = Gson().toJson(hour)

    @TypeConverter
    fun stringToHour(string: String): Hour? = Gson().fromJson(string, Hour::class.java)


}