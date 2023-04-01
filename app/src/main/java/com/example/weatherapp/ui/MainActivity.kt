package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.weatherapp.data.SearchResponse
import com.example.weatherapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainActivityViewModel by viewModels()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBar.threshold = 3
        viewModel.getCities().observe(this, Observer<List<SearchResponse>>{
            val adapter = ArrayAdapter<SearchResponse>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, it as MutableList<SearchResponse>)
            binding.searchBar.setAdapter(adapter)
        })

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


    }



}