package com.example.weatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse

class WeekForecastAdapter(private val forecastInfo: ForecastResponse?) :
    RecyclerView.Adapter<WeekForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return WeekForecastAdapter.ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeekForecastAdapter.ForecastViewHolder, position: Int) {
        holder.bindCurrHour(forecastInfo?.forecast?.forecastday?.get(position)?.date?.subSequence(5,10).toString())
        holder bindCurrIcon("https:"+forecastInfo?.forecast?.forecastday?.get(position)?.day?.condition?.icon)
        holder.bindCurrTemp(forecastInfo?.forecast?.forecastday?.get(position)?.day?.avgtemp_c.toString() +"°C")
    }

    override fun getItemCount(): Int {
        return 7
    }

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCurrHour(info: String) {
            val currentHour: TextView = itemView.findViewById(R.id.current_hour)
            currentHour.text = info

        }

        infix fun bindCurrIcon(info: String) {
            val currentHourIcon: ImageView = itemView.findViewById(R.id.current_hour_icon)
            currentHourIcon.load(info) {
                error(R.drawable.ic_launcher_background)
            }
        }

        fun bindCurrTemp(info: String) {
            val currentTemperature: TextView = itemView.findViewById(R.id.current_hour_temperature)
            currentTemperature.text = info
        }
    }


}