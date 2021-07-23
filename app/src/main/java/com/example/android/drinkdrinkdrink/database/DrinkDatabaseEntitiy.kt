package com.example.android.drinkdrinkdrink.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.android.drinkdrinkdrink.domain.Drink
import java.util.*

/**
 * Eintrag der Datenbank [DrinkDatabase]
 */
@Entity
class DrinkDatabaseEntitiy (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val volume: Int,
    val time: Date)

/**
 * Wandelt einen Datenbankeintrag in ein DomainModel um
 */
fun List<DrinkDatabaseEntitiy>.asDomainModel(): List<Drink>{
    return map{
        Drink(
            volume = it.volume,
            time = it.time
        )
    }
}

/**
 * Konvertierer, der es ermöglicht Daten in einer Datenbank zu speichern
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}