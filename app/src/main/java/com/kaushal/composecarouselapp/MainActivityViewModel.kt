package com.kaushal.composecarouselapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var autoScrollEnabled by mutableStateOf(true)

    var currentCarouselIndex by mutableIntStateOf(0)

    var listItems = DataRepository.getListItem()

    private var carouselItem = DataRepository.getCarouselItem()

    init {
        startAutoScroll()
    }

    private fun startAutoScroll() {
        viewModelScope.launch {
            while(true) {
                if(autoScrollEnabled) {
                    delay(3000)
                    updateNextItem(true)
                }
            }
        }
    }

    fun updateAutoScrollEnabled(enabled: Boolean) {
        autoScrollEnabled = enabled
        println("AutoScroll updated to: $enabled")
    }

    fun updateNextItem(isAutoScrollEnabled: Boolean) {
        currentCarouselIndex = (currentCarouselIndex + 1) % carouselItem.size
        updateAutoScrollEnabled(isAutoScrollEnabled)
    }

    fun updatePreviousItem(isAutoScrollEnabled: Boolean) {
        if (currentCarouselIndex > 0) {
            currentCarouselIndex--
        }
        updateAutoScrollEnabled(isAutoScrollEnabled)
    }

}