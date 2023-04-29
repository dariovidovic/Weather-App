package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.Location
import com.example.weatherapp.data.WeatherViewModel
import com.example.weatherapp.databinding.ActivityCityItemBinding
import java.text.SimpleDateFormat
import java.util.*


class CityItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCityItemBinding
    private lateinit var weatherViewModel: WeatherViewModel

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_item)

        binding = ActivityCityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this@CityItemActivity

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val currDate: String
        val currTime: String
        val calendar: Calendar
        val date: SimpleDateFormat
        val time: SimpleDateFormat

        calendar = Calendar.getInstance()
        date = SimpleDateFormat("E, LLLL dd")
        time = SimpleDateFormat("HH:mm aaa (z)")
        currDate = date.format(calendar.time).toString()
        currTime = time.format(calendar.time).toString().uppercase()


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
            currentDate.text = currDate
            currentTime.text = currTime

            val values = arrayListOf<String>(
                context.getString(
                    R.string.min_max_temp,
                    currentCity.forecast.forecastday[0].day.mintemp_c.toString(),
                    currentCity.forecast.forecastday[0].day.maxtemp_c.toString()
                ),
                context.getString(
                    R.string.wind_value,
                    currentCity.current.wind_kph.toString(),
                    currentCity.current.wind_dir
                ),
                context.getString(R.string.percentage, currentCity.current.humidity.toString()),
                context.getString(
                    R.string.pressure_value,
                    currentCity.current.pressure_mb.toString()
                ),
                context.getString(R.string.visibility_value, currentCity.current.vis_km.toString()),
                context.getString(R.string.accuracyMock)

            )
            val gridAdapter = GridViewAdapter(context, values)
            binding.weatherGrid.adapter = gridAdapter
        }
        val currentDayAdapter = ForecastAdapter(currentCity, "currentDay")
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.currentRecyclerView.layoutManager = linearLayoutManager
        binding.currentRecyclerView.adapter = currentDayAdapter

        val forecastAdapter = ForecastAdapter(currentCity, "weekForecast")
        val forecastLinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.weekRecyclerView.layoutManager = forecastLinearLayoutManager
        binding.weekRecyclerView.adapter = forecastAdapter

        binding.currentTemperatureApiIcon.setOnClickListener {
            weatherViewModel.addCity(currentCity)
            Toast.makeText(context, "${currentCity.current.condition}", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "ADDING THE DATA", Toast.LENGTH_SHORT).show()
            Toast.makeText(
                context,
                "${weatherViewModel.getCities().value?.current?.condition}",
                Toast.LENGTH_SHORT
            ).show()
            //weatherViewModel.addLocation(currentCity.location)
            //Toast.makeText(context, "${weatherViewModel.readAllData.toString()}", Toast.LENGTH_LONG).show()
        }

        binding.currentTemperature.setOnClickListener {
            weatherViewModel.readAllData
            Toast.makeText(context, "READING THE DATA", Toast.LENGTH_SHORT).show()
        }


    }


}