package com.example.android.drinkdrinkdrink.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Basis-Url der Api
 */
private const val BASE_URL = "https://www.7timer.info/bin/"

/**
 * Netzwerkinterface
 */
interface WeatherApiService {
    /**
     * Schickt eine Get-Anfrage an die zusammengesetzte Url und gibt die Antwort zurück
     */
    @GET("civillight.php")
    suspend fun getDataAsync(@Query("lon") longitude: Double, @Query("lat") latitude: Double, @Query("output") output: String = "json"): WeatherProperty
}

/**
 * Ein öffentliches Api Objekt, das den Retrofit-Dienst beinhaltet
 */
object Network {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val weather: WeatherApiService = retrofit.create(WeatherApiService::class.java)
}