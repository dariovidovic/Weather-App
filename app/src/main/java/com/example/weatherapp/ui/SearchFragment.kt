package com.example.weatherapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.example.weatherapp.R
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

        viewModel.getCities().observe(viewLifecycleOwner){
            val adapter = ArrayAdapter<SearchResponse>(requireActivity(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, it as MutableList<SearchResponse>)
            binding.searchBar.setAdapter(adapter)
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