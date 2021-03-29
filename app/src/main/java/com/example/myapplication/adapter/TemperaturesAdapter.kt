package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ForecastItemBinding
import com.example.myapplication.model.ForecastData
import com.example.myapplication.utils.loadImage
import com.example.myapplication.view.TemperaturesFragmentDirections

class TemperaturesAdapter(private var temperatures: List<ForecastData>, val cityName: String) :
    RecyclerView.Adapter<TemperaturesAdapter.TemperaturesViewHolder>() {
    class TemperaturesViewHolder(private val binding: ForecastItemBinding, val cityName: String) :
        RecyclerView.ViewHolder(binding.root) {
        fun setTemps(tempData: ForecastData) {
            binding.tvTemp.text = String.format("Temp: %d", tempData.main.temp.toInt())
            binding.tvDesc.text = tempData.weather[0].main
            val iconId = tempData.weather[0].icon + ".png"
            binding.imgForecast.loadImage("https://openweathermap.org/img/w/$iconId")
            binding.root.setOnClickListener {
                val action =
                    TemperaturesFragmentDirections.actionTemperaturesFragmentToDetailViewFragment(
                        tempData,
                        cityName
                    )
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperaturesViewHolder {
        val binding = ForecastItemBinding.inflate(LayoutInflater.from(parent.context))
        return TemperaturesViewHolder(binding, cityName)
    }

    override fun onBindViewHolder(holder: TemperaturesViewHolder, position: Int) {
        holder.setTemps(temperatures[position])
    }

    override fun getItemCount(): Int {
        return temperatures.size
    }
}