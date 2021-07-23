package com.example.android.drinkdrinkdrink

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.android.drinkdrinkdrink.fragments.InputFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.history.HistoryFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.main.MainFragmentDirections
import com.example.android.drinkdrinkdrink.repository.WeatherRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    /**
     * LocationManager des Systems mit dem der Standort bestimmt werden kann
     */
    private lateinit var locationManager: LocationManager

    /**
     * Wetter-Repository, das den Internetzugriff verwaltet
     */
    private val weatherRepository = WeatherRepository()

    /**
     * Höchsttemperatur des Tages abhängig vom Standort
     */
    val temperature: LiveData<Int>
        get() = _temperature
    private val _temperature = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Actionbar verstecken
        supportActionBar?.hide()

        Timber.i("onCreate()")
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //Bottom Navigation initialisieren
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //Start setzen
        bottomNavigation.selectedItemId = R.id.bottom_navigation_main
        //Fragmentwechsel
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            val navController = findNavController(R.id.fragmentContainerView)
            when(item.itemId) {
                R.id.bottom_navigation_main -> {
                    when(navController.currentDestination?.label){
                        "history_fragment" -> navController.navigate(HistoryFragmentDirections.actionHistoryFragmentToMainFragment())
                        "input_fragment" -> navController.navigate(InputFragmentDirections.actionInputFragmentToMainFragment())
                    }
                    true
                }
                R.id.bottom_navigation_input -> {
                    when(navController.currentDestination?.label){
                        "main_fragment" -> navController.navigate(MainFragmentDirections.actionMainFragmentToInputFragment())
                        "history_fragment" -> navController.navigate(HistoryFragmentDirections.actionHistoryFragmentToInputFragment())
                    }
                    true
                }
                R.id.bottom_navigation_history -> {
                    when(navController.currentDestination?.label){
                        "main_fragment" -> navController.navigate(MainFragmentDirections.actionMainFragmentToHistoryFragment())
                        "input_fragment" -> navController.navigate(InputFragmentDirections.actionInputFragmentToHistoryFragment())
                    }
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Prüft, ob die benötigten Freigaben erteilt wurden und ermittelt daraufhin per GPS den Standort
     */
    private fun calculateLocation() {
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 1000F, LocationListener {
                it.let {
                    getTemperature(it.longitude, it.latitude)
                }
            })
        }

    }

    /**
     * Startet eine Koroutine, die die Termperatur abfragt, und setzt die LiveData
     */
    private fun getTemperature(longitude: Double, latitude: Double){
        GlobalScope.launch {
            _temperature.postValue(weatherRepository.getTemperature(longitude, latitude))
        }
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart()")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart()")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume()")
        calculateLocation()
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause()")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy()")
    }
}