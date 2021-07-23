package com.example.android.drinkdrinkdrink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.android.drinkdrinkdrink.fragments.HistoryFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.InputFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.main.MainFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Actionbar verstecken
        supportActionBar?.hide()

        Timber.i("onCreate()")

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