package me.lancer.comicface_kotlin.ui.application

import android.app.Application

import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

class   App : Application() {
    private val TAG = App::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        val maxMem = Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(maxMem / 8)).build()
    }
}
