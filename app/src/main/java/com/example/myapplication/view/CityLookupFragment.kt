package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentCityLookupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityLookupFragment : Fragment() {
    private lateinit var binding: FragmentCityLookupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCityLookupBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLookup.setOnClickListener {
            if (binding.etCityName.text.toString().isNotBlank()) {
                val action =
                    CityLookupFragmentDirections.actionCityLookupFragmentToTemperaturesFragment(
                        binding.etCityName.text.toString()
                    )
                Navigation.findNavController(binding.root).navigate(action)
            } else {
                Toast.makeText(binding.root.context, "Please provide a city", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }
}