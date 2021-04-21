package com.edtechgrademy.com.grademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler(Looper.myLooper()!!).postDelayed({
            if(user != null) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, SignupActivity::class.java))
                finish()
            }
        }, 2000)

    }
}