package com.example.android.drinkdrinkdrink.domain

import com.example.android.drinkdrinkdrink.database.DrinkDatabaseEntity
import java.util.*

/**
 * Beinhaltet alle DomainModels
 */

data class Drink (val volume: Int,
                  val time: Date)

/**
 * Wandelt einen [Drink] in eine [DrinkDatabaseEntity] um
 */
fun Drink.asDatabaseEntity(): DrinkDatabaseEntity{
    return DrinkDatabaseEntity(
        id = 0,
        volume = this.volume,
        time = this.time
    )
}