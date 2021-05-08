package com.edtechgrademy.com.grademy.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edtechgrademy.com.grademy.R
import com.github.barteksc.pdfviewer.PDFView
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class ViewPdfActivity : AppCompatActivity() {

    private lateinit var pdfUrl: String
    private lateinit var pdfView: PDFView
    private lateinit var pbPdfLoader: CircularRevealCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)

        init()
        showPdfUsingPdfViewerLibrary()

    }


    private fun init() {
        pdfView = findViewById(R.id.pdfView)
        pbPdfLoader = findViewById(R.id.pbPdfLoader)
        pdfUrl = intent.getStringExtra("pdf_url").toString()
    }

    private fun showPdfUsingPdfViewerLibrary() {
        PdfDownload().execute(pdfUrl)
    }

    inner class PdfDownload : AsyncTask<String, Unit, InputStream>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pbPdfLoader.visibility = View.VISIBLE
        }
        override fun doInBackground(vararg params: String?): InputStream {
            lateinit var inputStream : BufferedInputStream
            val url = URL(params[0])
            val urlConn : HttpURLConnection = url.openConnection() as HttpURLConnection
            if(urlConn.responseCode == 200){
                inputStream = BufferedInputStream(urlConn.inputStream)
            }
            return inputStream
        }

        override fun onPostExecute(result: InputStream?) {
            pdfView.fromStream(result).load()
            pbPdfLoader.visibility = View.GONE
        }



    }

}