package com.example.android.drinkdrinkdrink.fragments.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Empfohlenes Trinkvolumen pro Tag
 */
private const val VOLUME_PER_DAY = 1500.0

class MainViewModel(application: Application): ViewModel() {

    /**
     * Fortschritt des Tagesziels in Prozent
     */
    val progressionTodayPercent: LiveData<Int>
        get() = _progressionTodayPercent
    private val _progressionTodayPercent = MutableLiveData<Int>(0)

    /**
     * Tagesfortschritt
     */
    val progressionToday: LiveData<Int>
        get() = _progressionToday
    private val _progressionToday = MutableLiveData<Int>(0)

    /**
     * Berechnet und setzt den Trinkfortschritt des aktuellen Tages
     */
    fun setProgressionToday (addition: Int){
        _progressionToday.value = addition
        _progressionTodayPercent.value = (addition/VOLUME_PER_DAY * 100.0).toInt()

        if(_progressionTodayPercent.value?.compareTo(100) == 1)
            _progressionTodayPercent.value = 100
    }

}