package com.example.android.drinkdrinkdrink.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Schnittstelle der Room-Datenbank [DrinkDatabase]
 */
@Dao
interface DrinkDatabaseDao {
    /**
     * Fügt einen Eintrag der Datenbank hinzu
     */
    @Insert
    fun insert(data: DrinkDatabaseEntitiy)

    /**
     * Löscht alle Einträge der Datenbank
     */
    @Query("DELETE FROM drinkdatabaseentitiy")
    fun clear()

    /**
     * Gibt alle Datenbankeinträge aus
     */
    @Query("SELECT * FROM drinkdatabaseentitiy ORDER BY time DESC")
    fun getAll(): LiveData<List<DrinkDatabaseEntitiy>>
}