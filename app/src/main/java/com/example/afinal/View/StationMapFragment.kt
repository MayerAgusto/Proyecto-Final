package com.example.afinal.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.afinal.R
import com.example.afinal.ViewModel.MapStationViewModel
import com.example.afinal.databinding.FragmentStationMapBinding


class StationMapFragment : Fragment() {
    private var _binding:FragmentStationMapBinding? = null
    private val binding get() = _binding!!
    private val mapStationViewModel:MapStationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStationMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapStationViewModel.initializeBinding(binding, this)
    }
}