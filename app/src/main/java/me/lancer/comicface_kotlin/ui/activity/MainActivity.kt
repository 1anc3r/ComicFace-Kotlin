package me.lancer.comicface_kotlin.ui.activity

import android.content.Intent
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.activity_main.*
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.book.fragment.HomeFragment
import me.lancer.comicface_kotlin.mvp.book.fragment.RankFragment
import me.lancer.comicface_kotlin.ui.adapter.ViewPagerAdapter
import android.support.v7.app.*
import java.util.*
import android.os.Bundle
import android.view.*
import me.lancer.comicface_kotlin.mvp.book.fragment.SortFragment

class MainActivity : AppCompatActivity() {

    val strings: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        val fragments = java.util.ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(RankFragment())
        fragments.add(SortFragment())
        val titles = strings.map(this::getString)
        viewPager.adapter = ViewPagerAdapter(fragments, titles, supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tab.setupWithViewPager(viewPager)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_about) {
            val intent = Intent(this, AboutActivity().javaClass)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
