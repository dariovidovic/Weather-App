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

class CurrentDayForecastAdapter(private val forecastInfo: ForecastResponse?): RecyclerView.Adapter<CurrentDayForecastAdapter.CurrentDayForecastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentDayForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false)
        return CurrentDayForecastAdapter.CurrentDayForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CurrentDayForecastViewHolder, position: Int) {
                holder.bindCurrHour(forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(position)?.time?.subSequence(11,16).toString())
                holder bindCurrIcon("https:"+forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(position)?.condition?.icon)
                holder.bindCurrTemp(forecastInfo?.forecast?.forecastday?.get(0)?.hour?.get(position)?.temp_c.toString()+"°C")
    }

    override fun getItemCount(): Int {
        return 7
    }

    class CurrentDayForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCurrHour(info: String) {
            val currentHour : TextView = itemView.findViewById(R.id.current_hour)
            currentHour.text = info
            /*val currHourIcon : ImageView = itemView.findViewById(R.id.current_hour_icon)
            val currHourTemp : TextView = itemView.findViewById(R.id.current_hour_temperature)*/

        }
        infix fun bindCurrIcon(info: String){
            val currentHourIcon : ImageView = itemView.findViewById(R.id.current_hour_icon)
            currentHourIcon.load(info){
                error(R.drawable.ic_launcher_background)
            }
        }

        fun bindCurrTemp(info: String){
            val currentTemperature : TextView = itemView.findViewById(R.id.current_hour_temperature)
            currentTemperature.text = info
        }
    }
}
