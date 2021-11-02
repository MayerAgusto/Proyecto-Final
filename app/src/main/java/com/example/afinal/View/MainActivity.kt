package com.example.afinal.View

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.afinal.ViewModel.LoginViewModel
import com.example.afinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //RegisterFragment
        val fragment = RegisterFragment()

        binding.btRegister.setOnClickListener {
            loginViewModel.showFragment(this,fragment)
        }


    }

}