package me.lancer.comicface_kotlin.util

import com.squareup.okhttp.OkHttpClient

object OkClient{
    private val client = OkHttpClient()
    fun instance() = client
}
