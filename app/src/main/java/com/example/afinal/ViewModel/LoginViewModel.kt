package com.example.afinal.ViewModel

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.afinal.R
import com.example.afinal.View.MainActivity
import com.example.afinal.View.RegisterFragment
import com.example.afinal.View.SecondActivity
import com.example.afinal.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private var activity:MainActivity? = null
    private var binding: ActivityMainBinding? = null
    lateinit var auth: FirebaseAuth


    fun initializeLoginView(activity: MainActivity, binding: ActivityMainBinding){
        this.activity = activity
        this.binding = binding

        auth = FirebaseAuth.getInstance()
        actionsLogin()
    }
    private fun actionsLogin(){
        val currentUser = auth.currentUser
        if(currentUser != null){
            activity?.startActivity(Intent(activity, SecondActivity::class.java))
        }
        binding?.btRegister?.setOnClickListener {
            createFragment()
        }
        binding?.btLogin?.setOnClickListener {
            loginFirebase()
        }
    }
    private fun loginFirebase(){
        if(TextUtils.isEmpty(binding?.etEmail?.text.toString())){
            binding?.etEmail?.error = "Correo electronico invalido"
            return
        }else if(TextUtils.isEmpty(binding?.etPassword?.text.toString())){
            binding?.etPassword?.error = "Contraseña Invalida"
            return
        }
        auth.signInWithEmailAndPassword(binding?.etEmail?.text.toString(), binding?.etPassword?.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    goToSecondActivity()
                }else{
                    showMessage("Error de ingreso, Intente mas tarde")
                }
            }
    }
    private fun createFragment(){
        val fragment = RegisterFragment()
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.frRegister, fragment)
            addToBackStack(null)
            commit()
        }
    }
    private fun showMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
    private fun goToSecondActivity(){
        val intent  = Intent(activity, SecondActivity::class.java)
        activity?.startActivity(intent)
    }
}