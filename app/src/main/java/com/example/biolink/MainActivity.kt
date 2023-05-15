package com.example.biolink

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById<WebView>(R.id.webView)

//        webViewSetUp(webView)

        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        // Configure related browser settings
        webView.apply {
            settings.javaScriptEnabled = true
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        }

        // Configure the client to use when opening URLs
        webView.webViewClient = object : WebViewClient() {
            var progressDialog: ProgressDialog? = ProgressDialog(this@MainActivity)
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                progressDialog?.apply {
                    setTitle("Loading...")
                    setMessage("Please wait...")
                    setCancelable(false)
                    show()
                }
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                progressDialog?.dismiss()
            }
        }

        webView.apply {
            // Enable responsive layout
            settings.useWideViewPort = true

            // Zoom out if the content width is greater than the width of the viewport
//            settings.loadWithOverviewMode = true
//            settings.setSupportZoom(false)

//            settings.builtInZoomControls = true // allow pinch zoom
//            settings.displayZoomControls = true // display the default zoom controls on the page

            loadUrl("https://anikadhikari.bio.link/")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}



//    @SuppressLint("SetJavaScriptEnabled")
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun webViewSetUp(webView: WebView) {
//
//        webView.webViewClient = WebViewClient()
//
//
//        // Configure related browser settings
//        webView.apply {
//            settings.javaScriptEnabled = true
//            settings.safeBrowsingEnabled = true
//
//            loadUrl("https://anikadhikari.bio.link/")
//        }
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
//            webView.goBack()
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }
