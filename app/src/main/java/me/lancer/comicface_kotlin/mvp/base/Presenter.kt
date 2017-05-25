package me.lancer.comicface_kotlin.mvp.base

interface Presenter<T> {

    fun obtain(url: String): T
}