package com.example.android.drinkdrinkdrink.fragments.history

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.android.drinkdrinkdrink.database.DrinkDatabase
import com.example.android.drinkdrinkdrink.database.getDrinkDatabase
import com.example.android.drinkdrinkdrink.repository.DrinkRepository

class HistoryViewModel(application: Application): ViewModel() {

    /**
     * Datenbank und Repository der Trinkeinträge
     */
    private val drinkDatabase: DrinkDatabase = getDrinkDatabase(application)
    private val drinkRepository: DrinkRepository = DrinkRepository(drinkDatabase)

    /**
     * Feld, in dem die heutigen Einträge gespeichert sind
     */
    val drinks = drinkRepository.drinks

}