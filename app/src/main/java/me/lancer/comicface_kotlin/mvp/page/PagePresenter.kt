package me.lancer.comicface_kotlin.mvp.page

import me.lancer.comicface_kotlin.mvp.base.Presenter
import me.lancer.comicface_kotlin.mvp.model.Page
import me.lancer.comicface_kotlin.util.getHtml
import me.lancer.comicface_kotlin.util.log
import org.json.JSONObject
import java.util.*

class PagePresenter() : Presenter<ArrayList<Page>> {

    override fun obtain(url: String): ArrayList<Page> {
        val list = ArrayList<Page>()
        val html = getHtml(url)
        val all = JSONObject(html)
        val code = all.getInt("code")
        if (code == 1) {
            val data = all.getJSONObject("data")
            val stateCode = data.getInt("stateCode")
            val message = data.getString("message")
            if (stateCode == 1 && message.contains("成功")) {
                val returnData = data.getJSONObject("returnData")
                val imageList = returnData.getJSONArray("image_list")
                var i: Int = 0
                while (i < imageList.length()) {
                    val image = imageList.get(i) as JSONObject
                    val title = image.getString("image_id")
                    val link = image.getString("location")
                    val bean = Page(title, link)
                    list.add(bean)
                    i++
                }

            }
        }
        return list;
    }

}