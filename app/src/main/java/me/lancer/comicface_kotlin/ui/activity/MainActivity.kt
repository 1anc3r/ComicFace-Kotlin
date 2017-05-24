package me.lancer.comicface_kotlin.ui.activity

import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.activity_main.*
import me.lancer.comicface_kotlin.R
import me.lancer.comicface_kotlin.mvp.book.fragment.HomeFragment
import me.lancer.comicface_kotlin.mvp.book.fragment.RankFragment
import me.lancer.comicface_kotlin.mvp.book.fragment.SearchFragment
import me.lancer.comicface_kotlin.ui.adapter.ViewPagerAdapter

class MainActivity : android.support.v7.app.AppCompatActivity() {

    val strings : java.util.ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        val fragments = java.util.ArrayList<Fragment>()
        fragments.add(HomeFragment())
        fragments.add(RankFragment())
        fragments.add(SearchFragment())
        val titles = strings.map(this::getString)
        viewPager.adapter = ViewPagerAdapter(fragments, titles, supportFragmentManager)
        viewPager.offscreenPageLimit = 2
        tab.setupWithViewPager(viewPager)
    }


    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_about) {
            val intent = android.content.Intent(this, AboutActivity().javaClass)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
