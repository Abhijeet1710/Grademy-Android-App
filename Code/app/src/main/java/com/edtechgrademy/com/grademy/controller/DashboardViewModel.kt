package com.edtechgrademy.com.grademy.controller

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.edtechgrademy.com.grademy.model.CurrentUser
import com.google.firebase.auth.FirebaseAuth

class DashboardViewModel(val st : String) : ViewModel() {

    var currentFrag : String = "Home"
    var SPNAME : String = "THEME"
    var THEME_MODE : String = "THEME_MODE"
    var MODE : String = "SYSTEM_DEFAULT"

    private val mAuth = FirebaseAuth.getInstance()

    fun logOut() = mAuth.signOut()

    fun changeTheme(context: Context, to : String) {
        MODE = when(to.toUpperCase()) {
            "DARK" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                "DARK"
            }
            "LIGHT" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                "LIGHT"
            }
            else -> {
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                "SYSTEM_DEFAULT"
            }
        }
        saveTheme(context)
    }
    private fun saveTheme(context: Context) {
        val sharedPreferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(THEME_MODE, MODE)
        editor.commit()
    }
    fun getTheme(context: Context) : String {
        val sharedPreferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(THEME_MODE, MODE)!!
    }

    fun getUser() : CurrentUser = CurrentUser(
        mAuth.currentUser.displayName,
        mAuth.currentUser.email,
        mAuth.currentUser.photoUrl,
        mAuth.currentUser.phoneNumber
    )

}