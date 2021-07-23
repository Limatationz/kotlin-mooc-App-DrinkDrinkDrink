package com.example.android.drinkdrinkdrink.repository

import com.example.android.drinkdrinkdrink.network.Network
import com.example.android.drinkdrinkdrink.network.asDomainModel

class WeatherRepository {
    /**
     * Ruft die Wetterdaten der Api ab und konvertiert diese in ein DomainModel
     */
    suspend fun getTemperature(longitude: Double, latitude: Double) = Network.weather.getDataAsync(longitude, latitude).asDomainModel()
}