package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.databinding.ActivityCityItemBinding


class CityItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_item)

        binding = ActivityCityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentCity = intent.extras?.getSerializable("city") as ForecastResponse
        binding.run {
            cityName.text = currentCity.location.name
            currentTemperature.text = currentCity.current.temp_c.toString() + "°"
            currentTemperatureApiIcon.load("https:"+ currentCity.current.condition.icon){
                error(R.drawable.ic_launcher_background)
            }
            currentWeather.text = currentCity.current.condition.text
            currentDate.text = currentCity.location.localtime.subSequence(0,10)
            currentTime.text = currentCity.location.localtime.subSequence(11,16)
            minMaxValue.text = currentCity.forecast.forecastday[0].day.mintemp_c.toString()+"°"+" / "+currentCity.forecast.forecastday[0].day.maxtemp_c.toString()+"°"
            windValue.text = currentCity.current.wind_kph.toString() + " km/h" + "( ${currentCity.current.wind_dir})"
            humidityValue.text = currentCity.current.humidity.toString() + "%"
            pressureValue.text = currentCity.current.pressure_mb.toString() + " hPa"
            visibilityValue.text = currentCity.current.vis_km.toString() + " km"
            accuracyValue.text = "93%"

        }
        val currentDayAdapter = CurrentDayForecastAdapter(currentCity)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.currentRecyclerView.layoutManager = linearLayoutManager
        binding.currentRecyclerView.adapter = currentDayAdapter

        val forecastAdapter = WeekForecastAdapter(currentCity)
        val linearLayoutManagerr = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.weekRecyclerView.layoutManager = linearLayoutManagerr
        binding.weekRecyclerView.adapter = forecastAdapter




    }


}