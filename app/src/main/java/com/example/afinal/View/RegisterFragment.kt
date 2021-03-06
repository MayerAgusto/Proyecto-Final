package com.example.afinal.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.afinal.ViewModel.RegisterViewModel
import com.example.afinal.databinding.RegisterFragmentBinding

 class RegisterFragment: Fragment() {
    private var _binding:RegisterFragmentBinding? = null
    private val binding get() = _binding!!
     private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RegisterFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerViewModel.initializeBinding(binding, this)
    }
}