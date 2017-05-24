package me.lancer.comicface_kotlin.mvp.model

/**
 * Created by HuangFangzhi on 2017/5/23.
 */
data class URL(
        val HOME_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/boutiqueList?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia",
        val RANK_TITLE_URL: String = "http://app.u17.com/v3/appV3/android/phone/rank/list?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia",
        val RANK_CONTENT_URL: String = "http://app.u17.com/v3/appV3/android/phone/list/commonComicList?argName=sort&android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&argValue=",

        val KEYWORD_URL: String = "http://app.u17.com/v3/appV3/android/phone/search/relative?android_id=3000048383665174&v=3070099&model=GT-P5210&inputText=",
        val HOTWORD_URL: String = "http://app.u17.com/v3/appV3/android/phone/search/hotkeywords?android_id=3000048383665174&v=3070099&model=GT-P5210&t=1466134822&come_from=wandoujia",

        val BOOK_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/detail_static?android_id=0A00270000130000&v=3070099&model=VPhone&come_from=wandoujia&comicid=",
        val CHAPTER_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/chapterNew?v=3120100&chapter_id=",
        val CONTENT_URL: String = "http://app.u17.com/v3/appV3/android/phone/comic/chapterlist?android_id=3000048383665174&v=3070099&model=GT-P5210&come_from=wandoujia&comicid="
)