package com.example.weatherapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentMyCitiesBinding
import com.example.weatherapp.viewmodel.WeatherViewModel


class MyCitiesFragment : Fragment(), MenuProvider {
    private var _binding: FragmentMyCitiesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by activityViewModels()
    private var editStatus: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = WeatherAdapter()
        binding.recyclerViewTest.adapter = adapter
        binding.recyclerViewTest.layoutManager = linearLayoutManager

        viewModel.favCities.observe(viewLifecycleOwner) {
            adapter.setData(it.toMutableList())
        }
        return root

    }

    companion object {
        fun newInstance() = MyCitiesFragment()
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.my_cities_nav, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.calendar -> {
                Toast.makeText(context, "Not yet implemented, stay tuned!", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
            R.id.edit -> {
                editStatus = true
                activity?.invalidateOptionsMenu()
                return true
            }
            R.id.done -> {
                editStatus = false
                activity?.invalidateOptionsMenu()
                return true
            }
            else -> return false
        }

    }

    override fun onPrepareMenu(menu: Menu) {
        if (!editStatus) {
            menu.findItem(R.id.calendar).isVisible = true
            menu.findItem(R.id.edit).isVisible = true
            menu.findItem(R.id.done).isVisible = false
        } else {
            menu.findItem(R.id.calendar).isVisible = false
            menu.findItem(R.id.edit).isVisible = false
            menu.findItem(R.id.done).isVisible = true
        }
    }
}