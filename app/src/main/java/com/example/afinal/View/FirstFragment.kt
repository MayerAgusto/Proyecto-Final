package com.example.afinal.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.afinal.R
import com.example.afinal.ViewModel.FirstViewModel
import com.example.afinal.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private var _binding:FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val firstViewModel:FirstViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstViewModel.initializeBinding(binding,this)
    }

}