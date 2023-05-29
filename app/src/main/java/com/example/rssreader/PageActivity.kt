package com.example.rssreader

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class PageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)

        val webView = findViewById<WebView>(R.id.webView)
        val link = intent.getStringExtra("link")!!
        webView.webViewClient = WebViewClient()
        webView.loadUrl(link)
    }
}