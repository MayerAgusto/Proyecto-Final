package com.example.afinal.ViewModel

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.example.afinal.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.View.MainActivity
import com.example.afinal.View.MapFragment
import com.example.afinal.View.StationMapFragment
import com.example.afinal.databinding.FragmentFirstBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.jar.Manifest

class FirstViewModel: ViewModel() {
    lateinit var binding: FragmentFirstBinding
    lateinit var fragment:Fragment
    lateinit var auth: FirebaseAuth

    fun initializeBinding(binding: FragmentFirstBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment

        auth = FirebaseAuth.getInstance()
        actionsFirstFragment()
    }
    private fun actionsFirstFragment(){
        binding.btClose.setOnClickListener {
            auth.signOut()
            goToLogin()
        }
        binding.btPolicia.setOnClickListener {
            googleMapPolice()
        }
        binding.btCentroMujer.setOnClickListener {
            googleMapWomanStation()
        }
        binding.btSos.setOnClickListener {
            makeCall()
        }
    }
    private fun googleMapPolice(){
       val fragments = StationMapFragment()
        fragment.parentFragmentManager.beginTransaction().apply {
            replace(R.id.frMap, fragments)
            addToBackStack(null)
            commit()
        }
    }
    private fun googleMapWomanStation(){
        val fragments = MapFragment()
        fragment.parentFragmentManager.beginTransaction().apply {
            replace(R.id.frMap, fragments)
            addToBackStack(null)
            commit()
        }
    }

    private fun makeCall(){
        val phoneNumber = "100".trim()
        val intent = Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+Uri.encode(phoneNumber)))
        fragment.activity?.startActivity(intent)
    }
    private fun goToLogin(){
        val intent = Intent(fragment.activity, MainActivity::class.java)
        fragment.activity?.startActivity(intent)
    }
}