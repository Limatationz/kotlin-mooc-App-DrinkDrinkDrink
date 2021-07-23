package com.example.android.drinkdrinkdrink.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Room-Datenbank, die aus [DrinkDatabaseEntitiy]-Einträgen besteht
 */
@Database(entities = [DrinkDatabaseEntitiy::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DrinkDatabase : RoomDatabase() {
    abstract val drinkDao: DrinkDatabaseDao
}

/**
 * Singleton einer [DrinkDatabase]
 */
private lateinit var INSTANCE: DrinkDatabase

/**
 * Erstellt wenn nötig das Singleton und gibt es zurück
 */
fun getDrinkDatabase(context: Context): DrinkDatabase {
    synchronized(DrinkDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                DrinkDatabase::class.java,
                "drinks")
                .fallbackToDestructiveMigration() //Optional: Wird die Versionsnummer erhöht, werden alle Einträge der Datenbank gelöscht.
                .build()
        }
    }
    return INSTANCE
}