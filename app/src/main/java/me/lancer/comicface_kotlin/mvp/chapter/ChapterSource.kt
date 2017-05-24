package me.lancer.comicface_kotlin.mvp.chapter

import me.lancer.comicface_kotlin.mvp.base.Source
import me.lancer.comicface_kotlin.mvp.model.Chapter
import me.lancer.comicface_kotlin.mvp.model.URL
import me.lancer.comicface_kotlin.util.getHtml
import me.lancer.comicface_kotlin.util.log
import org.json.JSONObject
import org.jsoup.Jsoup
import java.util.*

class ChapterSource() : Source<ArrayList<Chapter>> {

    override fun obtain(url: String): ArrayList<Chapter> {
        val list = ArrayList<Chapter>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.equals("成功")) {
                val returnData = data.getJSONObject("returnData")
                val chapterList = returnData.getJSONArray("chapter_list")
                var i: Int = 0
                while (i < chapterList.length()) {
                    val chapter = chapterList.get(i) as JSONObject
                    val title = chapter.getString("name")
                    val link = URL().CHAPTER_URL + chapter.getInt("chapter_id")
                    val bean = Chapter(title, link)
                    list.add(bean)
                    i++
                }

            }
        }
        return list;
    }

}