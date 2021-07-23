package com.example.android.drinkdrinkdrink.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.drinkdrinkdrink.databinding.HistoryFragmentBinding

class HistoryFragment: Fragment() {

    /**
     * Binding-object des [HistoryFragment]s
     */
    private lateinit var binding: HistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = HistoryFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}