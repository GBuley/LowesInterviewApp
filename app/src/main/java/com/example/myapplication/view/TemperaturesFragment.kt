package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.TemperaturesAdapter
import com.example.myapplication.databinding.FragmentTemperaturesBinding
import com.example.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemperaturesFragment : Fragment() {
    private val viewModel by viewModels<MainViewModel>()
    private val args: TemperaturesFragmentArgs by navArgs()
    private lateinit var binding: FragmentTemperaturesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTemperaturesBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = args.cityName
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        viewModel.currentForecast.observe(viewLifecycleOwner, { forecast ->
            forecast?.let {
                val adapter = TemperaturesAdapter(forecast.list, args.cityName)
                val layoutManager = LinearLayoutManager(binding.root.context)
                binding.rvTemperatures.adapter = adapter
                binding.rvTemperatures.layoutManager = layoutManager
            } ?: Toast.makeText(
                context,
                "Could not find data for cityname: ${args.cityName}",
                Toast.LENGTH_LONG
            ).show()
        })
        viewModel.getCurrentForecast(args.cityName)
    }

}