package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.WeatherViewModel
import com.example.weatherapp.databinding.FragmentMyCitiesBinding


class MyCitiesFragment : Fragment() {

    private var _binding: FragmentMyCitiesBinding? = null

    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMyCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val forecastResponse: MutableList<ForecastResponse?> = arrayListOf()

        /*viewModel.getCities().observe(viewLifecycleOwner) {
            forecastResponse.add(it)
            val adapter = WeatherAdapter(forecastResponse)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }*/

        return root

    }

    companion object {

        fun newInstance() = MyCitiesFragment()
    }

}