package com.edtechgrademy.com.grademy.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import com.edtechgrademy.com.grademy.model.PdfModel
import com.edtechgrademy.com.grademy.utils.Util
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel(vt: String) : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    val listEnglish = ArrayList<PdfModel>()

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