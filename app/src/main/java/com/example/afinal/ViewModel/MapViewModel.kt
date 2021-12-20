package com.example.afinal.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.databinding.FragmentMapBinding

class MapViewModel:ViewModel(){
    lateinit var binding: FragmentMapBinding
    lateinit var fragment:Fragment
    fun initializeBinding(binding: FragmentMapBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment
        //llamar a las funciones
        changeText()
    }
    //crear las funciones con logica aqui
    fun changeText(){
        //en esta rfuncion cambiaremos el texto del fragment
        //para llamar los objetos del fragmento usamos la variable binding y el id de elemento
        val texto:String = "En esta vista pones el mapa de"+"\n"+"los centro de emergencia"
        binding.tvMap.text = texto
    }
}
