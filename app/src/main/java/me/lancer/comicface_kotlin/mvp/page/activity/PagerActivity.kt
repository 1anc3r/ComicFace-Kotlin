package me.lancer.comicface_kotlin.mvp.page.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.content_main.*
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.model.Page
import me.lancer.comicface_kotlin.mvp.page.PagePresenter
import me.lancer.comicface_kotlin.mvp.page.fragment.PageFragment
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.util.ArrayList

class PagerActivity : AppCompatActivity() {

    companion object {
        val INTENT_LINK = "link"
    }

    lateinit var link: String
    var mData = ArrayList<Page>()
    lateinit var adapter: PageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        init()
    }

    private fun init() {
        link = intent.getStringExtra(INTENT_LINK)
        adapter = PageAdapter(mData, supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
    }

    override fun onResume() {
        super.onResume()
        async() {
            val data = PagePresenter().obtain(link)
            mData = data
            uiThread {
                adapter.refreshData(data)
            }
        }
    }

    class PageAdapter(var data: ArrayList<Page> = ArrayList<Page>(), fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

        override fun getCount(): Int = data.size

        override fun getItem(position: Int): Fragment? = newInstance(data[position].link)

        fun refreshData(newData: ArrayList<Page>) {
            data = newData
            notifyDataSetChanged()
        }

        fun newInstance(link: String) = PageFragment(link)
    }
}
