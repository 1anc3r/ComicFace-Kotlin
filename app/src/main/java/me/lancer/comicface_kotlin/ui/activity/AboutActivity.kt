package me.lancer.comicface_kotlin.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import me.lancer.comicface_kotlin.R
import kotlinx.android.synthetic.main.activity_about.*
import android.webkit.WebResourceRequest
import android.webkit.WebViewClient
import android.webkit.WebSettings
import android.webkit.WebView


class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        collapsingToolbar.title = getString(R.string.about_name)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView.getSettings().setSupportZoom(true)
        webView.getSettings().setBuiltInZoomControls(true)
        webView.getSettings().setUseWideViewPort(true)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN)
        webView.getSettings().setLoadWithOverviewMode(true)
        webView.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return false
            }

        })
        webView.loadUrl("https://github.com/1anc3r")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
