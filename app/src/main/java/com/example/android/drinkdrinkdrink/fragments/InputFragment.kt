package com.example.android.drinkdrinkdrink.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.drinkdrinkdrink.database.DrinkDatabase
import com.example.android.drinkdrinkdrink.database.getDrinkDatabase
import com.example.android.drinkdrinkdrink.databinding.InputFragmentBinding
import com.example.android.drinkdrinkdrink.domain.Drink
import com.example.android.drinkdrinkdrink.repository.DrinkRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class InputFragment: Fragment() {

    /**
     * Binding-object  des [InputFragment]s
     */
    private lateinit var binding: InputFragmentBinding

    /**
     * Datenbank und Repository der Trinkeinträge
     */
    private lateinit var drinkDatabase: DrinkDatabase
    private lateinit var drinkRepository: DrinkRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = InputFragmentBinding.inflate(layoutInflater, container, false)

        drinkDatabase = getDrinkDatabase(requireActivity().applicationContext)
        drinkRepository = DrinkRepository(drinkDatabase)

        //Wenn Speichern gedrückt wird, wird der Drink in DrinkDatabase gespeichert
        binding.saveButton.setOnClickListener {
            val drink = Drink((binding.volumeSpinner.selectedItem as String).toInt(), Date())
            GlobalScope.launch {
                drinkRepository.insertDrink(drink)
            }
        }

        return binding.root
    }
}