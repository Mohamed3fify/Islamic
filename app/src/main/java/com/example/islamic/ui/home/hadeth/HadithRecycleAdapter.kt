package com.example.islamic.ui.home.hadeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islamic.databinding.ItemHadethTitleBinding
import model.Hadith

class HadithRecycleAdapter(private val hadithList: List<Hadith>) :
    RecyclerView.Adapter<HadithRecycleAdapter.MyViewHolder>() {
    class MyViewHolder(val itemBinding: ItemHadethTitleBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hadith = hadithList[position]
        holder.itemBinding.title.text = hadith.title
        onItemClickListener?.let {listener->
            holder.itemView.setOnClickListener {
                listener.onItemClick(hadith , position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewBinding: ItemHadethTitleBinding = ItemHadethTitleBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = hadithList.size
    var onItemClickListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun onItemClick(item : Hadith, position: Int)
    }
}