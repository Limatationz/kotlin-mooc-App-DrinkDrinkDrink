package com.example.android.drinkdrinkdrink.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.drinkdrinkdrink.databinding.MainFragmentBinding
import timber.log.Timber


class MainFragment : Fragment() {

    /**
     * Binding-object des [MainFragment]s
     */
    private lateinit var binding: MainFragmentBinding

    /**
     * Refernenz auf das zugehörige [MainViewModel]
     */
    private lateinit var viewModel: MainViewModel

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        //Initialisiert das viewModel
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MainViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        // Beobachtet viewmodel.progressionTodayPercent und übergibt den Wert bei Änderung updateProgressBar
        viewModel.progressionTodayPercent.observe(viewLifecycleOwner, {
            updateProgressBar(it)
        })

        // Beobachtet viewmodel.progressionToday und setzt den Wert als Text der TextView dataTodayTextView
        viewModel.progressionToday.observe(viewLifecycleOwner, {
            binding.dataTodayTextView.text = it.toString()
        })
        
        return binding.root
    }

    /**
     * Setzt den Text der [todaysProgressTextView] und die [todaysProgressProgressBar] auf die übergebene Prozentzahl [progr]
     */
    private fun updateProgressBar(progr: Int) {
        binding.todaysProgressProgressBar.progress = progr
        binding.todaysProgressTextView.text = "$progr%"
    }

}