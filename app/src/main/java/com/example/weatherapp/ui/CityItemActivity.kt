package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.databinding.ActivityCityItemBinding


class CityItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityItemBinding
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_item)

        binding = ActivityCityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this@CityItemActivity

        val currentCity = intent.extras?.getSerializable("city") as ForecastResponse
        binding.collapsingToolbarLayout.title = currentCity.location.name
        binding.collapsingToolbarLayout.expandedTitleMarginStart = 40
        binding.collapsingToolbarLayout.expandedTitleMarginTop = 180
        binding.run {
            currentTemperature.text = context.getString(
                R.string.currentTemperature,
                currentCity.current.temp_c.toString()
            )
            currentTemperatureApiIcon.load(
                context.getString(
                    R.string.api_icon_url,
                    currentCity.current.condition.icon
                )
            ) {
                error(R.drawable.ic_launcher_background)
            }
            currentWeather.text = currentCity.current.condition.text
            currentDate.text = currentCity.location.localtime.subSequence(0, 10)
            currentTime.text = currentCity.location.localtime.subSequence(11, 15)
            minMaxValue.text = context.getString(
                R.string.min_max_temp,
                currentCity.forecast.forecastday[0].day.mintemp_c.toString(),
                currentCity.forecast.forecastday[0].day.maxtemp_c.toString()
            )
            windValue.text = context.getString(
                R.string.wind_value,
                currentCity.current.wind_kph.toString(),
                currentCity.current.wind_dir
            )
            humidityValue.text =
                context.getString(R.string.percentage, currentCity.current.humidity.toString())
            pressureValue.text = context.getString(
                R.string.pressure_value,
                currentCity.current.pressure_mb.toString()
            )
            visibilityValue.text =
                context.getString(R.string.visibility_value, currentCity.current.vis_km.toString())
            accuracyValue.text = context.getString(R.string.accuracyMock)


        }
        val currentDayAdapter = CurrentDayForecastAdapter(currentCity)
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.currentRecyclerView.layoutManager = linearLayoutManager
        binding.currentRecyclerView.adapter = currentDayAdapter

        val forecastAdapter = WeekForecastAdapter(currentCity)
        val forecastLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.weekRecyclerView.layoutManager = forecastLinearLayoutManager
        binding.weekRecyclerView.adapter = forecastAdapter


    }


}