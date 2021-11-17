package com.example.afinal.ViewModel
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.afinal.View.MainActivity
import com.example.afinal.View.SecondActivity
import com.example.afinal.databinding.ActivitySecondBinding
import com.google.firebase.auth.FirebaseAuth

class SecondViewModel: ViewModel() {
    lateinit var activity:SecondActivity
    lateinit var binding: ActivitySecondBinding
    lateinit var auth: FirebaseAuth

    fun initializeSecondViewModel(activity: SecondActivity, binding: ActivitySecondBinding){
        this.activity = activity
        this.binding = binding
        //actionsSecondView()

        auth = FirebaseAuth.getInstance()

    }

    private fun actionsSecondView(){
        /*binding.btClose.setOnClickListener {
            auth.signOut()
            goToLogin()
        }*/
    }
    private fun goToLogin(){
        val intent = Intent(activity,MainActivity::class.java)
        activity.startActivity(intent)
    }
}