package com.naveeniiit119.news

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class newsAdapter(private val listener: NewsItemClicked):RecyclerView.Adapter<NewsViewHolder>() {
    private  val  items:ArrayList<items> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return items.size;
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(newsArray: ArrayList<items>) {
        items.clear()
        items.addAll(newsArray)
        notifyDataSetChanged()

    }

}

class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val imageView:ImageView=itemView.findViewById(R.id.image)
    val titleView:TextView=itemView.findViewById(R.id.title)
}
interface NewsItemClicked{
    fun onItemClicked(items: items)
}