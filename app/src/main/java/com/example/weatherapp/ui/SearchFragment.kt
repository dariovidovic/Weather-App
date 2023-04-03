package com.example.weatherapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.viewmodel.CitiesViewModel
import kotlinx.coroutines.*


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!
    private val viewModel: CitiesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchBar.threshold = 3

        var forecastReponse : MutableList<ForecastResponse?> = arrayListOf()

        viewModel.getCities().observe(viewLifecycleOwner){
            val adapter = ArrayAdapter<SearchResponse>(requireActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, it as MutableList<SearchResponse>)
            binding.searchBar.setAdapter(adapter)

            binding.searchBar.onItemClickListener
        }




        val textWatcher : TextWatcher = object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.searchBar.text.count()>2)
                    viewModel.makeApiCall(binding.searchBar.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        }
        binding.searchBar.addTextChangedListener(textWatcher)

        binding.searchBar.setOnItemClickListener(OnItemClickListener { parent, arg1, pos, id ->
            viewModel.makeForecastApiCall(binding.searchBar.text.toString())
            //Log.d("TEEEEST", viewModel.getForecast().value.toString())

        })

        viewModel.getForecast().observe(viewLifecycleOwner){
            forecastReponse.add(it)
        }



        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WeatherAdapter(forecastReponse)

        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = adapter


        return root
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}