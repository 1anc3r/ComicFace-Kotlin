package me.lancer.comicface_kotlin.mvp.book.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.lancer.comicface_kotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*
import me.lancer.comicface_kotlin.mvp.model.Book
import java.util.*
import android.support.v7.widget.StaggeredGridLayoutManager
import org.jetbrains.anko.backgroundColor


class SearchAdapter(var data: List<Book> = ArrayList(), var itemClick: (View, Int) -> Unit)
    : RecyclerView.Adapter<SearchAdapter.BookViewHolder>() {

    var palette = arrayOf(R.color.red, R.color.pink, R.color.purple, R.color.deeppurple, R.color.indigo, R.color.blue, R.color.lightblue, R.color.cyan, R.color.teal, R.color.green, R.color.lightgreen, R.color.lime, R.color.yellow, R.color.amber, R.color.orange, R.color.deeporange)

    companion object {
        val TYPE_TITLE: Int = 0
        val TYPE_CONTENT: Int = 1
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    private fun bindView(itemView: View, position: Int) {
        val book = data[position]
        itemView.tvTitle.text = book.title
        itemView.cardView.setCardBackgroundColor(itemView.getResources().getColor(palette[position%16]))
        itemView.container.setOnClickListener {
            itemClick(itemView, position)
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        if (data[position].type === 0) {
            return TYPE_TITLE
        } else if (data[position].type === 1) {
            return TYPE_CONTENT
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BookViewHolder? {
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return BookViewHolder(itemView)
    }

    fun refreshData(newData: List<Book>) {
        data = newData
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}