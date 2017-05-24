package me.lancer.comicface_kotlin.mvp.chapter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.lancer.comicface_kotlin.R

import kotlinx.android.synthetic.main.item_chapter.view.*
import me.lancer.comicface_kotlin.mvp.model.Chapter
import java.util.*


class ChapterAdapter(var data: List<Chapter> = ArrayList(), val itemClick: (View, Int) -> Unit)
: RecyclerView.Adapter<ChapterAdapter.PageViewHolder>() {

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    private fun bindView(itemView: View, position: Int) {
        val page = data[position]
        itemView.tvTitle.text = page.title
        itemView.tvTitle.setOnClickListener { itemClick(itemView, position) }
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): PageViewHolder? {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chapter, parent, false)
        return PageViewHolder(itemView)
    }

    fun refreshData(newData: List<Chapter>) {
        data = newData
        notifyDataSetChanged()
    }

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
