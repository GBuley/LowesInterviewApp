package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentDetailViewBinding
import com.example.myapplication.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailViewFragment : Fragment() {
    private lateinit var binding: FragmentDetailViewBinding
    private val args: DetailViewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailViewBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val temp = args.specificWeatherData.main.feels_like.toInt()
        val iconId = args.specificWeatherData.weather[0].icon
        binding.apply {
            titleTemp.text = args.specificWeatherData.main.temp.toInt().toString()
            tvFeelsLike.text = String.format("Feels Like: %d", temp)
            tvDesc.text = args.specificWeatherData.weather[0].main
            tvSubDesc.text = args.specificWeatherData.weather[0].description
            imgForecastDetail.loadImage("https://openweathermap.org/img/w/$iconId.png")
            toolbar.title = args.cityName
            toolbar.setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}