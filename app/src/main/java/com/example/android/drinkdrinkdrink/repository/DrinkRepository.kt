package com.example.android.drinkdrinkdrink.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.drinkdrinkdrink.database.DrinkDatabase
import com.example.android.drinkdrinkdrink.database.asDomainModel
import com.example.android.drinkdrinkdrink.domain.Drink
import com.example.android.drinkdrinkdrink.domain.asDatabaseEntity
import java.util.*


class DrinkRepository (private val drinkDatabase: DrinkDatabase){
    /**
     * Liste an [Drink]s, die der [drinkDatabase] hinzugefügt wurden
     */
    val drinks: LiveData<List<Drink>> = Transformations.map(drinkDatabase.drinkDao.getAll()) {
        it.asDomainModel()
    }

    /**
     * Zeitstempel von heute morgen 0:00
     */
    private val todayTimestamp by lazy {
        val now = Date().time
        Date(now - now % (24 * 60 * 60 * 1000)).time
    }

    /**
     * Liste an [Drink]s, die nach [todayTimestamp] der [drinkDatabase] hinzugefügt wurden
     */
    val drinksToday: LiveData<List<Drink>> = Transformations.map(drinkDatabase.drinkDao.getToday(todayTimestamp)) {
        it.asDomainModel()
    }

    /**
     * Fügt einen [drink] der [drinkDatabase] hinzu
     */
    suspend fun insertDrink(drink: Drink){
            drinkDatabase.drinkDao.insert(drink.asDatabaseEntity())
    }
}