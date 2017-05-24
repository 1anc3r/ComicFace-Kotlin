package me.lancer.comicface_kotlin.mvp.book.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.chapter.activtiy.ChapterActivity
import me.lancer.comicface_kotlin.mvp.book.BookSource
import me.lancer.comicface_kotlin.mvp.book.adapter.BookAdapter
import me.lancer.comicface_kotlin.mvp.model.Book
import me.lancer.comicface_kotlin.mvp.model.URL
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.ArrayList

/**
 * Created by HuangFangzhi on 2017/5/23.
 */

class RankFragment() : Fragment() {

    companion object {
        val AIM_URL :String = URL().RANK_TITLE_URL
    }

    var mData = ArrayList<Book>()
    lateinit var adapter: BookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View) {
        swipeRefresh = view.findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
        swipeRefresh.setColorSchemeResources(R.color.blue, R.color.teal, R.color.green, R.color.yellow, R.color.orange, R.color.red, R.color.pink, R.color.purple);
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = BookAdapter { _: View, i: Int -> jump2Chapter(i) }
        recyclerView.adapter = adapter
        swipeRefresh.setOnRefreshListener {
            load()
        }
        swipeRefresh.post { swipeRefresh.isRefreshing = true }
    }

    private fun jump2Chapter(position: Int) {
        var intent = Intent(context, ChapterActivity().javaClass)
        intent.putExtra(ChapterActivity.INTENT_LINK, mData[position].link)
        intent.putExtra(ChapterActivity.INTENT_COVER, mData[position].cover)
        intent.putExtra(ChapterActivity.INTENT_TITLE, mData[position].title)
        intent.putExtra(ChapterActivity.INTENT_CATEGORY, mData[position].category)
        startActivity(intent)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            load()
        }
    }

    private fun load() {
        async() {
            val data = BookSource().rankTitle(AIM_URL)
            uiThread {
                mData = data
                adapter.refreshData(data)
                swipeRefresh.isRefreshing = false
            }
        }
    }
}