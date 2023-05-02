package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.viewmodel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityCityItemBinding
import java.text.SimpleDateFormat
import java.util.*


class CityItemActivity : AppCompatActivity() {

    private var favouriteStatus: Boolean = false
    private var currentCityId: Int = 0
    private var currentCityTemp: ForecastResponse? = null
    private lateinit var binding: ActivityCityItemBinding
    private lateinit var weatherViewModel: WeatherViewModel

    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_city_item)

        binding = ActivityCityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val context = this@CityItemActivity

        weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val currDate: String
        val currTime: String

        val calendar: Calendar = Calendar.getInstance()
        val date = SimpleDateFormat("E, LLLL dd")
        val time = SimpleDateFormat("HH:mm aaa (z)")
        currDate = date.format(calendar.time).toString()
        currTime = time.format(calendar.time).toString().uppercase()


        val currentCity = intent.extras?.getSerializable("city") as ForecastResponse
        favouriteStatus = currentCity.isFavourite
        currentCityTemp = currentCity
        currentCityId = currentCity.forecastId
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

            val values = arrayListOf(
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

        binding.currentTemperature.setOnClickListener {
            weatherViewModel.setFavStatus(currentCityId, false)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (!favouriteStatus) {
            menu?.findItem(R.id.action_fav)?.isVisible = true
            menu?.findItem(R.id.action_unfav)?.isVisible = false
        } else {
            menu?.findItem(R.id.action_fav)?.isVisible = false
            menu?.findItem(R.id.action_unfav)?.isVisible = true
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_fav -> {
                currentCityTemp?.isFavourite = true
                weatherViewModel.addCity(currentCityTemp)
                Toast.makeText(this, "You saved this city!", Toast.LENGTH_SHORT).show()
                favouriteStatus = true
                invalidateOptionsMenu()
            }
            R.id.action_unfav -> {
                favouriteStatus = false
                weatherViewModel.deleteCity(currentCityId)
                Toast.makeText(this, "Removed from favourites", Toast.LENGTH_SHORT).show()
                invalidateOptionsMenu()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}