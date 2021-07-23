package com.example.android.drinkdrinkdrink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.android.drinkdrinkdrink.fragments.InputFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.HistoryFragmentDirections
import com.example.android.drinkdrinkdrink.fragments.MainFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Actionbar verstecken
        supportActionBar?.hide()

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
}