package com.example.afinal.ViewModel


import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.afinal.R
import com.example.afinal.View.MainActivity


class LoginViewModel: ViewModel() {

    fun showFragment(activity: MainActivity, fragment: Fragment){
        activity.supportFragmentManager.beginTransaction().apply {
            replace(R.id.frRegister, fragment)
            addToBackStack(null)
            commit()
        }
    }



}