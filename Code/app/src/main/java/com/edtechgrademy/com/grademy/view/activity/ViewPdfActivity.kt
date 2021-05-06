package com.edtechgrademy.com.grademy.view.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edtechgrademy.com.grademy.R
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class ViewPdfActivity : AppCompatActivity() {

    private lateinit var pdfUrl: String
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)

        init()
        showPdf()
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE

    }

    private fun init() {
        webView = findViewById(R.id.wvPdf)
        pdfUrl = intent.getStringExtra("pdfUrl").toString()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showPdf() {
        Toast.makeText(this, "show begin", Toast.LENGTH_SHORT).show()

        webView.requestFocus()
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

//        val myPdfUrl = "http://www.pdf995.com/samples/pdf.pdf"
        val myPdfUrl = "https://firebasestorage.googleapis.com/v0/b/grademy-e155a.appspot.com/o/MH-Board%2FSSC%2FEnglish%2FPdfs%2FAdjective.pdf?alt=media&token=63380844-ed71-4723-ad2b-ec36a69f0a58"
//        val myPdfUrl = "https://www.cs.cmu.edu/afs/cs.cmu.edu/user/gchen/www/download/java/LearnJava.pdf"
        val url = "https://docs.google.com/viewer?embedded=true&url=${URLEncoder.encode(myPdfUrl, "ISO-8859-1")}";
        webView.loadUrl(url);

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view : WebView, url : String) : Boolean {
                view.loadUrl(url);
                return true;
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, progress: Int) {
                if (progress < 100) {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                }
                if (progress == 100) {
                }
            }
        }

    }

    private fun show() {
        Toast.makeText(this, "show", Toast.LENGTH_SHORT).show()
        val yourURL = "https://firebasestorage.googleapis.com/v0/b/grademy-e155a.appspot.com/o/MH-Board%2FSSC%2FEnglish%2FPdfs%2FAdverb.pdf?alt=media&token=df09a375-6108-4383-88dd-c662b79cc2af"

        val url = "http://docs.google.com/gview?embedded=true&url=$yourURL"
        val doc =
            "<iframe src='$url' width='100%' height='100%' style='border: none;'></iframe>"
        webView.settings.javaScriptEnabled = true
        webView.loadData(doc, "text/html", "UTF-8")
    }

}