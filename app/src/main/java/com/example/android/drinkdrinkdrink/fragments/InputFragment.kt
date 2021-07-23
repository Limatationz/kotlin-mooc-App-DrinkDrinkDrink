package com.example.android.drinkdrinkdrink.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.drinkdrinkdrink.databinding.InputFragmentBinding

class InputFragment: Fragment() {

    /**
     * Binding-object  des [InputFragment]s
     */
    private lateinit var binding: InputFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = InputFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }
}