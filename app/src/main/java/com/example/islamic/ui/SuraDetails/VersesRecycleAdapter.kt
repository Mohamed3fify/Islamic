package com.example.islamic.ui.SuraDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamic.databinding.ItemVerseBinding

class VersesRecycleAdapter(private val verse: List<String>) :
    RecyclerView.Adapter<VersesRecycleAdapter.MyViewHolder>() {
    class MyViewHolder(val itemBinding: ItemVerseBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = verse[position]
        holder.itemBinding.content.text = title
        onItemClickListener?.let {listener->
            holder.itemView.setOnClickListener {
                listener.onItemClick(title, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding: ItemVerseBinding = ItemVerseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = verse.size
    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(item: String, position: Int)
    }
}