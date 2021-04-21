package com.edtechgrademy.com.grademy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.edtechgrademy.com.grademy.R
import com.edtechgrademy.com.grademy.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    lateinit var binding : ActivityDashboardBinding
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

    }
}