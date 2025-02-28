package com.kaushal.composecarouselapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private var autoScrollEnabled by mutableStateOf(true)

    var currentCarouselIndex by mutableIntStateOf(0)

    var listItems = DataRepository.getListItem()

    private var carouselItem = DataRepository.getCarouselItem()

    private var autoScrollJob: Job? = null

    init {
        startAutoScroll()
    }

    private fun startAutoScroll() {
        stopAutoScroll() // Clear any old job to avoid duplicates
        autoScrollJob = viewModelScope.launch {
            while(isActive) {
                if(autoScrollEnabled) {
                    delay(3000)
                    updateNextItem()
                }
            }
        }
    }

    private fun stopAutoScroll() {
        autoScrollJob?.cancel()
        autoScrollJob = null
    }

    fun updateAutoScrollEnabled(enabled: Boolean) {
        println("AutoScroll updated to: $enabled")
        autoScrollEnabled = enabled
        if(enabled) {
            if(autoScrollJob?.isActive == false) startAutoScroll()
        } else stopAutoScroll()
    }

    fun updateNextItem() {
        currentCarouselIndex = (currentCarouselIndex + 1) % carouselItem.size
    }

    fun updatePreviousItem() {
        if (currentCarouselIndex > 0) {
            currentCarouselIndex--
        }
    }

}