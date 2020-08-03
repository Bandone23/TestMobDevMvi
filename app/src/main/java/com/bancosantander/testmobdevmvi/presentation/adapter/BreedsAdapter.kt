package com.bancosantander.testmobdevmvi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bancosantander.testmobdevmvi.R
import com.bancosantander.testmobdevmvi.databinding.ItemBreedsBinding
import com.bancosantander.testmobdevmvi.presentation.adapter.holder.BreedsNHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_breeds.view.*

class BreedsAdapter (
    private var items: ArrayList<String>,
   private val clickListener: (String, Int) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding: ItemBreedsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_breeds,
                parent,
                false
            )
        return BreedsNHolder(itemBinding)
    }



    override fun getItemCount():Int =items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item = items[position]
        holder as BreedsNHolder
        holder.binEvent(item,clickListener)
    }




}