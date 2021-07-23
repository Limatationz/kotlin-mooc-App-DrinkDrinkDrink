package com.example.android.drinkdrinkdrink.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.drinkdrinkdrink.databinding.HistoryFragmentBinding

class HistoryFragment: Fragment() {

    /**
     * Binding-object des [HistoryFragment]s
     */
    private lateinit var binding: HistoryFragmentBinding

    /**
     * Refernenz auf das zugehörige [HistoryViewModel]
     */
    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = HistoryFragmentBinding.inflate(layoutInflater, container, false)

        //ViewModel initialisieren
        val application = requireNotNull(this.activity).application
        val viewModelFactory = HistoryViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)

        //RecyclerView initialisieren
        val historyDrinksAdapter = HistoryAdapter()
        binding.historyRecyclerView.adapter = historyDrinksAdapter

        //Daten der RecyclerView setzen
        viewModel.drinks.observe(viewLifecycleOwner, {
            historyDrinksAdapter.data = it
        })

        return binding.root
    }
}