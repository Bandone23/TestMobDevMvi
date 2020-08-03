package com.bancosantander.testmobdevmvi.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bancosantander.testmobdevmvi.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_breeds.view.*

class BreedsAdapter (
    private var items: ArrayList<String>
  //  private val clickListener: (String, Int) -> Unit
): RecyclerView.Adapter<BreedsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(breeds: String) {
            itemView.title_breeds.text = breeds
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_breeds, parent,
                false
            )
        )

    override fun getItemCount():Int =items.size
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(items[position])


    fun addData(list: List<String>) {
        items.addAll(list)
    }

}