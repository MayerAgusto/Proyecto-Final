package com.example.afinal.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.databinding.FragmentStationMapBinding

class MapStationViewModel:ViewModel() {
    lateinit var binding: FragmentStationMapBinding
    lateinit var fragment: Fragment

    fun initializeBinding(binding: FragmentStationMapBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment
        //llamar a las funciones
        changeText()
    }
    //crear las funciones con logica aqui
    fun changeText(){
        //en esta rfuncion cambiaremos el texto del fragment
        //para llamar los objetos del fragmento usamos la variable binding y el id de elemento
        val texto:String = "En esta vista pones el mapa de"+"\n"+"las estaciones policiales mas cercanas"
        binding.tvStationMap.text = texto
    }

}