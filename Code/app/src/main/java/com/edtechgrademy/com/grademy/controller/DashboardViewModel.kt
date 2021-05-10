package com.edtechgrademy.com.grademy.controller

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.edtechgrademy.com.grademy.model.CurrentUser
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.utils.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DashboardViewModel(val st: String) : ViewModel() {

    var currentFrag: String = "Home"
    var SPNAME: String = "THEME"
    var THEME_MODE: String = "THEME_MODE"
    var MODE: String = "SYSTEM_DEFAULT"

    private val mAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    val listEnglish = ArrayList<PdfModel>()

    fun logOut() = mAuth.signOut()

    fun changeTheme(context: Context, to: String) {
        MODE = when (to.toUpperCase()) {
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

    fun getTheme(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(THEME_MODE, MODE)!!
    }

    fun getUser(): CurrentUser = CurrentUser(
        mAuth.currentUser.displayName.split(" ")[0],
        mAuth.currentUser.displayName,
        mAuth.currentUser.email,
        mAuth.currentUser.photoUrl
//            mAuth.currentUser.phoneNumber,
    )

    fun getEnglishPdfs(myCallback: (ArrayList<PdfModel>) -> Unit) {

        if (listEnglish.size != 0 || listEnglish.isNotEmpty()) {
            myCallback(listEnglish)
            Log.d("TAG_LIST_STATUS", "List was downloaded")
        } else {
            Log.d("TAG_LIST_STATUS", "List was not downloaded")
            db.collection(Util.english)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        val list = ArrayList<PdfModel>()
                        for (document in task.result!!) {
                            val pdfModel = PdfModel()
                            pdfModel.pdfName = document.data["pdf_name"] as String
                            pdfModel.pdfThumbnail = document.data["pdf_thumbnail"] as String
                            pdfModel.pdfUrl = document.data["pdf_url"] as String
                            listEnglish.add(pdfModel)
                        }
                        myCallback(listEnglish)
                    } else {
//                    toast(, "Error getting documents.", task.exception)
                    }
                }
        }

    }


}



