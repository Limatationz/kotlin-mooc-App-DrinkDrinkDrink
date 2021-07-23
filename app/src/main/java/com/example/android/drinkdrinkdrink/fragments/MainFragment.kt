package com.example.android.drinkdrinkdrink.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.drinkdrinkdrink.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    /**
     * Binding-object des [MainFragment]s
     */
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        
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