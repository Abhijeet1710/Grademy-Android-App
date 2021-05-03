package com.edtechgrademy.com.grademy.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivG.animation = AnimationUtils.loadAnimation(this, R.anim.g_fade_in)
        binding.ivGrademy.animation = AnimationUtils.loadAnimation(this, R.anim.grademy_translate_up)


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