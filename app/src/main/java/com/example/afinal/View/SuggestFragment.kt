package com.example.afinal.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.afinal.R
import com.example.afinal.ViewModel.FirstViewModel
import com.example.afinal.ViewModel.SugestViewModel
import com.example.afinal.databinding.FragmentSuggestBinding


class SuggestFragment : Fragment() {
    private var _binding:FragmentSuggestBinding? = null
    private val binding get() = _binding!!
    private val suggestViewModel: SugestViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuggestBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        suggestViewModel.initializeBinding(binding, this)
    }

}