package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ForecastResponse
import com.example.myapplication.remote.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: ForecastRepository) : ViewModel() {
    private val _currentForecast = MutableLiveData<ForecastResponse>()
    val currentForecast: LiveData<ForecastResponse> = _currentForecast

    fun getCurrentForecast(cityName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentForecast.postValue(repo.getForecast(cityName))
        }
    }

}