package com.example.catcompendium.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catcompendium.databinding.LayoutCatBreedListItemBinding
import com.example.catcompendium.model.CatBreedItem

class CatListAdapter : PagingDataAdapter<CatBreedItem, CatListAdapter.CatBreedItemHolder>(CatListDiffCallback){

    class CatBreedItemHolder(private val binding: LayoutCatBreedListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(catBreedModel: CatBreedItem?) {
            catBreedModel?.let {
                binding.apply {
                    Glide.with(binding.root.context).load("https://cdn2.thecatapi.com/images/${catBreedModel.referenceImageId}.jpg")
                        .into(binding.ivBreedThumbnail)
                    tvBreedName.text = catBreedModel.name
                    tvBreedDesc.text = catBreedModel.description
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedItemHolder {
        return CatBreedItemHolder(LayoutCatBreedListItemBinding
            .inflate(LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatBreedItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object CatListDiffCallback: DiffUtil.ItemCallback<CatBreedItem>() {
    override fun areItemsTheSame(oldItem: CatBreedItem, newItem: CatBreedItem): Boolean {
        return oldItem.id === newItem.id
    }

    override fun areContentsTheSame(oldItem: CatBreedItem, newItem: CatBreedItem): Boolean {
        return oldItem == newItem
    }
}