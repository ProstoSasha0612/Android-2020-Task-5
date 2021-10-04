package com.hfad.android.funnycats

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.android.funnycats.R
import com.hfad.android.funnycats.databinding.CatItemBinding
import com.hfad.android.funnycats.model.Cat

class CatAdapter(val context: Context) :
    PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(itemComparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(itemBinding, context)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CatViewHolder(private val binding: CatItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat?) { //TODO make cat loading animation (it needs new logic)
            binding.title.text = cat?.title
            Glide.with(context)
                .load(cat?.imageUrl)
                .fitCenter()
                .placeholder(R.drawable.cat_laod)
                .into(binding.catImageview)
        }
    }

    companion object {
        val itemComparator = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }
        }
    }
}