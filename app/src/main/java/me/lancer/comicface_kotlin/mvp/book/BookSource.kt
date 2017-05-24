package me.lancer.comicface_kotlin.mvp.book

import me.lancer.comicface_kotlin.mvp.base.Source
import me.lancer.comicface_kotlin.mvp.model.Book
import me.lancer.comicface_kotlin.util.getHtml
import me.lancer.comicface_kotlin.util.log
import org.json.JSONObject
import me.lancer.comicface_kotlin.mvp.model.URL
import java.util.*

class BookSource() : Source<ArrayList<Book>> {

    override fun obtain(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val comicsLists = returnData.getJSONArray("comicLists")
                var i: Int = 0
                while (i < comicsLists.length()) {
                    val comicsList = comicsLists.get(i) as JSONObject
                    val comics = comicsList.getJSONArray("comics")
                    val itemTitle = comicsList.getString("itemTitle")
                    val itemIcon = comicsList.getString("titleIconUrl")
                    val bean = Book(itemTitle, "", 0, itemIcon, "")
                    list.add(bean)
                    var j: Int = 0
                    while (j < comics.length()) {
                        val comic = comics.get(j) as JSONObject
                        val title = comic.getString("name")
                        val category = comic.getString("short_description")
                        val cover = comic.getString("cover")
                        val link = URL().BOOK_URL + comic.getInt("comicId")
                        val bean = Book(title, category, 1, cover, link)
                        list.add(bean)
                        j++
                    }
                    i++
                }

            }
        }
        return list
    }

    fun rankTitle(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val rankingList = returnData.getJSONArray("rankinglist")
                var i: Int = 0
                while (i < rankingList.length()) {
                    val ranking = rankingList.get(i) as JSONObject
                    val title = ranking.getString("title")+"排行"
                    val category = ranking.getString("subTitle")
                    val cover = ranking.getString("cover")
                    val link = URL().RANK_CONTENT_URL+ranking.getInt("argValue")
                    val bean = Book(title, category, 0, cover, link)
                    list.add(bean)
                    list.addAll(rankContent(link))
                    i++
                }
            }

        }
        return list
    }

    fun rankContent(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val comics = returnData.getJSONArray("comics")
                var i: Int = 0
                while (i < comics.length()) {
                    val comic = comics.get(i) as JSONObject
                    val title = comic.getString("name")
                    val category = comic.getString("description")
                    val cover = comic.getString("cover")
                    val link = URL().BOOK_URL + comic.getInt("comicId")
                    val bean = Book(title, category, 1, cover, link)
                    list.add(bean)
                    i++
                }
            }
        }
        return list
    }

    fun hotword(url: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONArray("returnData")
                var i: Int = 0
                while (i < returnData.length()) {
                    val comic = returnData.get(i) as JSONObject
                    val title = comic.getString("tag")
                    val bean = Book(title, "", 1, "", "")
                    list.add(bean)
                    i++
                }
            }
        }
        return list
    }

    fun keyword(url: String, keyword: String): ArrayList<Book> {
        val list = ArrayList<Book>()
        val html = getHtml(url + keyword)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONArray("returnData")
                var i: Int = 0
                while (i < returnData.length()) {
                    val comic = returnData.get(i) as JSONObject
                    val title = comic.getString("name")
                    val link = URL().BOOK_URL + comic.getInt("comic_id")
                    val bean = Book(title, "", 1, "", link)
                    list.add(bean)
                    i++
                }
            }
        }
        return list
    }
}