package com.bancosantander.testmobdevmvi.presentation.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bancosantander.testmobdevmvi.databinding.ItemBreedsBinding
import kotlinx.android.synthetic.main.item_breeds.view.*

class BreedsNHolder (
    private val binding: ItemBreedsBinding
):RecyclerView.ViewHolder(binding.root){
    fun binEvent(breeds:String,clickListener: (String, Int) -> Unit) {
        binding.titleBreeds.text = breeds
        binding.containerBreeds.setOnClickListener {
            clickListener(breeds,binding.containerBreeds.id)
        }
        binding.executePendingBindings()


    }


}