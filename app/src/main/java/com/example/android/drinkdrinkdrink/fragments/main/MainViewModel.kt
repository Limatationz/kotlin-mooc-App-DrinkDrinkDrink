package com.example.android.drinkdrinkdrink.fragments.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.drinkdrinkdrink.database.DrinkDatabase
import com.example.android.drinkdrinkdrink.database.getDrinkDatabase
import com.example.android.drinkdrinkdrink.domain.Drink
import com.example.android.drinkdrinkdrink.repository.DrinkRepository
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(application: Application): ViewModel() {

    /**
     * Datenbank und Repository der Trinkeinträge
     */
    private val drinkDatabase: DrinkDatabase = getDrinkDatabase(application)
    private val drinkRepository: DrinkRepository = DrinkRepository(drinkDatabase)

    /**
     * Feld, in dem die heutigen Einträge gespeichert sind
     */
    val drinksToday = drinkRepository.drinksToday

    /**
     * Fortschritt des Tagesziels in Prozent
     */
    val progressionTodayPercent: LiveData<Int>
        get() = _progressionTodayPercent
    private val _progressionTodayPercent = MutableLiveData<Int>(0)

    /**
     * Text, der alle Eintröge des Tages formatiert beinhaltet
     */
    val progressionToday: LiveData<String>
        get() = _progressionToday
    private val _progressionToday = MutableLiveData<String>()

    /**
     * Tagesziel an Volumen
     */
    private var volumePerDay = 1500.0


    /**
     * Berechnet [volumePerDay] anhand der übergebenen [temperature]
     */
    fun calculateVolumePerDay(temperature: Int){
        if(temperature > 15){
            volumePerDay = 1500.00 + 100 * (temperature - 15)
            calculateProgressionToday(drinksToday.value)
        }
    }

    /**
     * Berechnet und setzt den Trinkfortschritt in Prozent des aktuellen Tages
     */
    private fun setProgressionToday(addition: Int){
        _progressionTodayPercent.value = (addition/volumePerDay * 100.0).toInt()

        if(_progressionTodayPercent.value?.compareTo(100) == 1)
            _progressionTodayPercent.value = 100
    }

    /**
     * Berechnet das getrunkene Volumen der [drinks] und deligiert an die beiden weiterführenden Methoden
     */
    fun calculateProgressionToday(drinks: List<Drink>?) {
        if(drinks != null){
            val volumeToday = drinks.sumOf { it.volume }
            setProgressionToday(volumeToday)
            _progressionToday.value = transformDrinksToday(drinks)
        }
    }

    /**
     * Formatiert die Liste an [drinks] in eine Zeichenkette
     */
    private fun transformDrinksToday(drinks: List<Drink>): String{
        var result = ""
        val local = Locale("de")
        val fmt = SimpleDateFormat("HH:mm", local)
        drinks.forEach {
            result += "${fmt.format(it.time)}: ${it.volume}ml\n"
        }
        result += "\nGesamt: ${drinks.sumOf { it.volume }}ml"
        return result
    }

}