package com.hfad.android.funnycats

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hfad.android.funnycats.databinding.CatItemBinding
import com.hfad.android.funnycats.model.Cat
import java.io.ByteArrayOutputStream

class CatAdapter(context: Context?) :
    PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(itemComparator) {

    val openDetailsCallback = context as OnItemCLickCallback

    interface OnItemCLickCallback {
        fun onItemCLickOpenDetails(byteArray: ByteArray)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = CatItemBinding.inflate(layoutInflater, parent, false)
        return CatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CatViewHolder(private val binding: CatItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(cat: Cat?) { //TODO make cat loading empty template
            binding.title.text = cat?.title
            Glide.with(itemView.context)
                .asBitmap()
                .load(cat?.imageUrl)
                .fitCenter()
                .placeholder(R.drawable.cat_laod)
                .into(binding.catImageview)
        }

        override fun onClick(v: View?) {
            val bitmap = (binding.catImageview.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray = stream.toByteArray()
            openDetailsCallback.onItemCLickOpenDetails(byteArray)
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