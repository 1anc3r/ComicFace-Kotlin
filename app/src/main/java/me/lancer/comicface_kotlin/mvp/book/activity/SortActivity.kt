package me.lancer.comicface_kotlin.mvp.chapter.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chapter.*
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.book.BookPresenter
import me.lancer.comicface_kotlin.mvp.book.adapter.BookAdapter
import me.lancer.comicface_kotlin.mvp.book.fragment.HomeFragment
import me.lancer.comicface_kotlin.mvp.page.activity.PagerActivity
import me.lancer.comicface_kotlin.mvp.chapter.ChapterPresenter
import me.lancer.comicface_kotlin.mvp.chapter.adapter.ChapterAdapter
import me.lancer.comicface_kotlin.mvp.model.Book
import me.lancer.comicface_kotlin.mvp.model.Chapter
import me.lancer.comicface_kotlin.mvp.model.URL
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class SortActivity : AppCompatActivity() {

    companion object {
        val INTENT_LINK = "link"
        val INTENT_COVER = "cover"
        val INTENT_TITLE = "title"
    }

    lateinit var link: String
    lateinit var cover: String
    lateinit var title: String
    var mData = ArrayList<Book>()
    lateinit var adapter: BookAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)
        init()
    }

    private fun init() {
        link = intent.getStringExtra(INTENT_LINK)
        cover = intent.getStringExtra(INTENT_COVER)
        title = intent.getStringExtra(INTENT_TITLE)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = title
        if (!cover.equals("")) {
            Picasso.with(this).load(cover).into(imageView)
        }
        swipeRefresh = find(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.blue, R.color.teal, R.color.green, R.color.yellow, R.color.orange, R.color.red, R.color.pink, R.color.purple)
        swipeRefresh.setOnRefreshListener { load() }
        recyclerView = find(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        adapter = BookAdapter { _: View, i: Int -> jump2Chapter(i) }
        recyclerView.adapter = adapter
    }

    private fun jump2Chapter(position: Int) {
        var intent = Intent(this, ChapterActivity().javaClass)
        intent.putExtra(ChapterActivity.INTENT_LINK, mData[position].link)
        intent.putExtra(ChapterActivity.INTENT_COVER, mData[position].cover)
        intent.putExtra(ChapterActivity.INTENT_TITLE, mData[position].title)
        intent.putExtra(ChapterActivity.INTENT_CATEGORY, mData[position].category)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        swipeRefresh.post { swipeRefresh.isRefreshing = true }
        load()
    }

    private fun load() {
        async() {
            val data = BookPresenter().sortContent(link)
            uiThread {
                mData = data
                adapter.refreshData(data)
                swipeRefresh.isRefreshing = false
            }
        }
    }
}
