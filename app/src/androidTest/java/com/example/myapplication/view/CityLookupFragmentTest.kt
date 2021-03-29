package com.example.myapplication.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.example.myapplication.R
import com.example.myapplication.util.ToastMatcher
import com.example.myapplication.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class CityLookupFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickLookupButton_navigateToTempFragment() {
        val cityName = "Dallas"
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<CityLookupFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.et_city_name)).perform(typeText(cityName))
        onView(withId(R.id.btn_lookup)).perform(click())
        verify(navController).navigate(
            CityLookupFragmentDirections.actionCityLookupFragmentToTemperaturesFragment(cityName)
        )
    }

    @Test
    fun clickLookupButtonWithNoText_DoesNotNavigate() {
        val cityName = ""
        launchFragmentInHiltContainer<CityLookupFragment> {

        }
        onView(withId(R.id.et_city_name)).perform(typeText(cityName))
        onView(withId(R.id.btn_lookup)).perform(click())
        onView(withText("Please provide a city")).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}