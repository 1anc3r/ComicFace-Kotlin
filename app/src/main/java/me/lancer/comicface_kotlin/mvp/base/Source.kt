package me.lancer.comicface_kotlin.mvp.base

interface Source<T> {

    fun obtain(url: String): T
}