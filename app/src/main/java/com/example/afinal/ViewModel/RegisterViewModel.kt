package com.example.afinal.ViewModel

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.Model.User
import com.example.afinal.View.MainActivity
import com.example.afinal.databinding.RegisterFragmentBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterViewModel:ViewModel() {
    private  var user:User = User()
    private var binding: RegisterFragmentBinding? = null
    private var fragment: Fragment? = null
    lateinit var auth:FirebaseAuth

    var databaseReference:DatabaseReference?= null
    var database:FirebaseDatabase? = null

    fun initializeBinding(binding: RegisterFragmentBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment
        loadData()
        actionsFragment()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://android-backend-8f0f1-default-rtdb.firebaseio.com/")
        databaseReference = database?.reference!!.child("profile")
    }
    private fun actionsFragment(){
        binding?.btRegisterCreateUser?.setOnClickListener {
            getData()
            saveData()
            saveFirebaseUser()
        }
    }
    private fun saveFirebaseUser(){
        if(user.getName().isEmpty()){
            binding?.etRegisterName?.error = "Dato invalido"
            return
        }else if(user.getLastName().isEmpty()){
            binding?.etRegisterLastName?.error  ="Dato invalido"
            return
        }else if(user.getEmail().isEmpty()){
            binding?.etRegisterEmail?.error = "Dato Invalido"
            return
        }else if(user.getPassword().isEmpty()){
            binding?.etRegisterPassword?.error = "Dato Invalido"
            return
        }
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val currentUser = auth.currentUser
                    val currentUserDb= databaseReference?.child((currentUser?.uid!!))
                    currentUserDb?.child("Name")?.setValue(user.getName())
                    currentUserDb?.child("LastName")?.setValue(user.getLastName())
                    currentUserDb?.child("Email")?.setValue(user.getEmail())
                    showToast("Bienvenid@ ${user.getName()} ")
                    goToMain()
                }else{
                    showToast("Error de registro, intentelo mas tarde")
                }
            }
    }
    private fun getData(){
        user.setName(binding?.etRegisterName?.text.toString())
        user.setLastName(binding?.etRegisterLastName?.text.toString())
        user.setEmail(binding?.etRegisterEmail?.text.toString())
        user.setPassword(binding?.etRegisterPassword?.text.toString())
    }
    private fun saveData(){
        val sharedPreferences = fragment?.activity?.getSharedPreferences("shared", 0)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("name", user.getName())
            putString("lastName", user.getLastName())
            putString("email", user.getEmail())
            putString("password", user.getPassword())
        }?.apply()
        showToast("Datos almacenados con exito")

    }
    private fun loadData(){
        val sharedPreferences = fragment?.activity?.getSharedPreferences("shared", 0)
        binding?.etRegisterName?.setText(sharedPreferences?.getString("name", ""))
        binding?.etRegisterLastName?.setText(sharedPreferences?.getString("lastName", ""))
        binding?.etRegisterEmail?.setText(sharedPreferences?.getString("email", ""))
        binding?.etRegisterPassword?.setText(sharedPreferences?.getString("password", ""))
    }
    private fun goToMain(){
        val intent  = Intent(fragment?.activity, MainActivity::class.java)
        fragment?.startActivity(intent)
    }

    private fun showToast(message:String){
        Toast.makeText(fragment?.activity, message, Toast.LENGTH_SHORT).show()
    }
}