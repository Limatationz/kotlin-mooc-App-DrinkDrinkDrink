package com.example.android.drinkdrinkdrink.network

import com.squareup.moshi.JsonClass

/**
 * Beinhaltet alle Networkproperties
 */

@JsonClass(generateAdapter = true)
data class WeatherProperty (
    val product: String,
    val init: String,
    val dataseries: List<DayProperty> )

@JsonClass(generateAdapter = true)
data class DayProperty (
    val date: String,
    val weather: String,
    val temp2m: TemperatureProperty,
    val wind10m_max: Int)

@JsonClass(generateAdapter = true)
data class TemperatureProperty (
    val max: Int,
    val min: Int)

/**
 * Wandelt eine [WeatherProperty] in einen [Int]-Wert um, der die heutige maximale Temperatur beschreibt
 */
fun WeatherProperty.asDomainModel() = this.dataseries[0].temp2m.max