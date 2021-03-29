package com.example.myapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.extensions.getOrAwaitValueTest
import com.example.myapplication.remote.FakeForecastRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private val repo = FakeForecastRepo()

    @Before
    fun setUp() {
        viewModel = MainViewModel(repo)
    }


    @Test
    fun `city name for currentForecast should be the same as submitted forecast`() {
        val garland = "Garland"
        viewModel.getCurrentForecast(garland)
        val forecast = viewModel.currentForecast.getOrAwaitValueTest()
        assertEquals(garland, forecast.city.name)

    }

    @Test
    fun `function to return null if city is empty`() {
        val emptyCityName = ""
        viewModel.getCurrentForecast(emptyCityName)
        val forecast = viewModel.currentForecast.getOrAwaitValueTest()
        assertNull(forecast)
    }

    @Test
    fun `function to return null if city is not found`() {
        val notACity = "something"
        viewModel.getCurrentForecast(notACity)
        val forecast = viewModel.currentForecast.getOrAwaitValueTest()
        assertNull(forecast)
    }

}