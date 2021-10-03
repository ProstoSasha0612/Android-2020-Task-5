package com.hfad.android.hotcats

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.android.hotcats.databinding.CatItemBinding
import com.hfad.android.hotcats.model.Cat

class CatAdapter(val context: Context) :
    ListAdapter<Cat, CatAdapter.CatViewHolder>(itemComparator) {

    private val items = mutableListOf<Cat>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(itemBinding, context)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addItems(list: List<Cat>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    class CatViewHolder(private val binding: CatItemBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) { //TODO make cat loading animation (it needs new logic)
            binding.title.text = cat.title
            Glide.with(context)
//                .asBitmap()
                .load(cat.imageUrl)
                .fitCenter()
                .placeholder(R.drawable.cat_laod)
                .into(binding.catImageview)
        }
    }

    companion object {//TODO change to equals
        val itemComparator = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }
}