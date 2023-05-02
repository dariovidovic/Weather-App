package com.example.weatherapp.ui


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.ForecastResponse
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.viewmodel.CitiesViewModel
import com.example.weatherapp.viewmodel.WeatherViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!
    private val viewModel: CitiesViewModel by activityViewModels()
    private val recentsViewModel : WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchBar.threshold = 3

        viewModel.getCities().observe(viewLifecycleOwner) {
            val adapter = ArrayAdapter(
                requireActivity(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                it as MutableList<SearchResponse>
            )
            binding.searchBar.setAdapter(adapter)

        }


        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.searchBar.text.count() > 2)
                    viewModel.makeApiCall(binding.searchBar.text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        }
        binding.searchBar.addTextChangedListener(textWatcher)
        binding.searchBar.setOnItemClickListener { _, _, _, _ ->
            viewModel.makeForecastApiCall(binding.searchBar.text.toString())
            viewModel.getForecast().observe(viewLifecycleOwner) {
                recentsViewModel.addCity(it)
                val intent = Intent(context, CityItemActivity::class.java)
                intent.putExtra("city", it )
                startActivity(intent)
            }

        }


        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val adapter = WeatherAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = linearLayoutManager

        recentsViewModel.readAllData.observe(viewLifecycleOwner){
            adapter.setData(it.toMutableList())
        }




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