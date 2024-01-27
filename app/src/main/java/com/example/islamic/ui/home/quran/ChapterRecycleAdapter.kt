package com.example.islamic.ui.home.quran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamic.databinding.ItemChapterTitleBinding

class ChapterRecycleAdapter(private val chapterList: List<String>) :
    RecyclerView.Adapter<ChapterRecycleAdapter.MyViewHolder>() {
    class MyViewHolder(val itemBinding: ItemChapterTitleBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = chapterList[position]
        holder.itemBinding.title.text = title
        onItemClickListener?.let {listener->
            holder.itemView.setOnClickListener {
                listener.onItemClick(title, position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding: ItemChapterTitleBinding = ItemChapterTitleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = chapterList.size
    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(item: String, position: Int)
    }
}