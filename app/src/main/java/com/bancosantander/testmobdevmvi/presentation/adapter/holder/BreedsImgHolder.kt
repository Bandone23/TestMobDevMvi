package com.bancosantander.testmobdevmvi.presentation.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.bancosantander.testmobdevmvi.databinding.ItemImgBreedsBinding
import com.bancosantander.testmobdevmvi.util.getImgPicasso

class BreedsImgHolder(
    private val binding: ItemImgBreedsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bindEvent(breedsImg: String) {
        getImgPicasso(breedsImg, binding.imgBreeds)
        binding.executePendingBindings()
    }
}