package com.edtechgrademy.com.grademy.controller

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class DashboardViewModel(val st : String) : ViewModel() {

    var currentFrag : String = "Home"

    private val mAuth = FirebaseAuth.getInstance()

    fun logOut() {
        mAuth.signOut()
    }
}