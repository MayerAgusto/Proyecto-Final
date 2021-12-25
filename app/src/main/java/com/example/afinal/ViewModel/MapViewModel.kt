package com.example.afinal.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.databinding.FragmentMapBinding
import com.example.afinal.R
import com.google.android.gms.maps.SupportMapFragment

class MapViewModel:ViewModel(){
    lateinit var binding: FragmentMapBinding
    lateinit var fragment:Fragment

    fun initializeBinding(binding: FragmentMapBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment
        changeText()

    }
    //crear las funciones con logica aqui
    fun changeText(){
        val texto:String = "En esta vista pones el mapa de"+"\n"+"los centro de emergencia"
        binding.tvMap.text = texto
    }



}

