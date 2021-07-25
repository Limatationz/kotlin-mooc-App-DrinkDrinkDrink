package com.example.android.drinkdrinkdrink.domain

import com.example.android.drinkdrinkdrink.database.DrinkDatabaseEntitiy
import java.util.*

/**
 * Beinhaltet alle DomainModels
 */

data class Drink (val volume: Int,
                  val time: Date)

/**
 * Wandelt einen [Drink] in eine [DrinkDatabaseEntitiy] um
 */
fun Drink.asDatabaseEntity(): DrinkDatabaseEntitiy{
    return DrinkDatabaseEntitiy(
        id = 0,
        volume = this.volume,
        time = this.time
    )
}