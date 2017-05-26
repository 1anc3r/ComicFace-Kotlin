package me.lancer.comicface_kotlin.mvp.chapter.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chapter.*
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.page.activity.PagerActivity
import me.lancer.comicface_kotlin.mvp.chapter.ChapterPresenter
import me.lancer.comicface_kotlin.mvp.chapter.adapter.ChapterAdapter
import me.lancer.comicface_kotlin.mvp.model.Chapter
import org.jetbrains.anko.async
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class ChapterActivity : AppCompatActivity() {

    companion object {
        val INTENT_LINK = "link"
        val INTENT_COVER = "cover"
        val INTENT_TITLE = "title"
        val INTENT_CATEGORY = "category"
    }

    lateinit var link: String
    lateinit var cover: String
    lateinit var title: String
    lateinit var category: String
    var mData = ArrayList<Chapter>()
    lateinit var adapter: ChapterAdapter
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
        category = intent.getStringExtra(INTENT_CATEGORY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingToolbar.title = title
        if (!cover.equals("")) {
            Picasso.with(this).load(cover).into(imageView)
        }
        swipeRefresh = find(R.id.swipeRefresh)
        swipeRefresh.setColorSchemeResources(R.color.blue, R.color.teal, R.color.green, R.color.yellow, R.color.orange, R.color.red, R.color.pink, R.color.purple)
        swipeRefresh.setOnRefreshListener { load() }
        recyclerView = find(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager?
        adapter = ChapterAdapter { _: View, i: Int -> jump2Pager(i) }
        recyclerView.adapter = adapter
    }

    private fun jump2Pager(position: Int) {
        var intent = Intent(this, PagerActivity().javaClass)
        intent.putExtra(PagerActivity.INTENT_LINK, mData[position].link)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        swipeRefresh.post { swipeRefresh.isRefreshing = true }
        load()
    }

    private fun load() {
        async() {
            val data = ChapterPresenter().obtain(link)
            uiThread {
                mData = data
                adapter.refreshData(data)
                swipeRefresh.isRefreshing = false
            }
        }
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
