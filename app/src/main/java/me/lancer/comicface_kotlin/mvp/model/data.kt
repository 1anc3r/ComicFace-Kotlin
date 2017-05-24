package me.lancer.comicface_kotlin.mvp.model

/**
 * Created by HuangFangzhi on 2017/5/23.
 */

data class Book(val title: String, val category: String, val type: Int, val cover: String, val link: String)

data class Chapter(val title: String, val link: String)

data class Page(val title: String, val link: String)