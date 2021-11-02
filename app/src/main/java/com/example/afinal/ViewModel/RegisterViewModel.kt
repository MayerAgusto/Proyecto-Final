package com.example.afinal.ViewModel


import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.Model.User
import com.example.afinal.View.MainActivity

class RegisterViewModel:ViewModel() {
    private  var user:User = User()


    fun getData(name:String, lastName:String, email:String, password:String){
        user.setName(name)
        user.setLastName(lastName)
        user.setEmail(email)
        user.setPassword(password)
    }
    fun saveData(fragment: Fragment){
        val sharedPreferences = fragment.activity?.getSharedPreferences("shared", 0)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString("name", user.getName())
            putString("lastName", user.getLastName())
            putString("email", user.getEmail())
            putString("password", user.getPassword())
        }?.apply()
        showToast(fragment, "Datos almacenados con exito")
        val intent  = Intent(fragment.activity, MainActivity::class.java)
        fragment.startActivity(intent)
    }
    fun loadData(fragment: Fragment): User{
        val sharedPreferences = fragment.activity?.getSharedPreferences("shared", 0)
        val name = sharedPreferences?.getString("name", "")
        val lastName = sharedPreferences?.getString("lastName", "")
        val email = sharedPreferences?.getString("email", "")
        val password = sharedPreferences?.getString("password", "")
        return User(name!!, lastName!!, email!!, password!!)
    }

    private fun showToast(fragment: Fragment, message:String){
        Toast.makeText(fragment.activity, message, Toast.LENGTH_SHORT).show()
    }

}