package com.bancosantander.testmobdevmvi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bancosantander.testmobdevmvi.R
import com.bancosantander.testmobdevmvi.databinding.ItemBreedsBinding
import com.bancosantander.testmobdevmvi.databinding.ItemImgBreedsBinding
import com.bancosantander.testmobdevmvi.presentation.adapter.holder.BreedsImgHolder
import com.bancosantander.testmobdevmvi.presentation.adapter.holder.BreedsNHolder

class BreedsImgAdapter(
    private var items: ArrayList<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding: ItemImgBreedsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_img_breeds,
                parent,
                false
            )
        return BreedsImgHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder as BreedsImgHolder
        holder.bindEvent(item)
    }

}