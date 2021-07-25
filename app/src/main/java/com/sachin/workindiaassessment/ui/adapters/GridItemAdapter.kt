package com.sachin.workindiaassessment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.workindiaassessment.databinding.ItemGridViewBinding
import com.sachin.workindiaassessment.databinding.ItemLinearViewBinding
import com.sachin.workindiaassessment.models.Item

class GridItemAdapter(
    private val itemsList: List<Item>
) : RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {

    class ViewHolder(private val gridViewBinding: ItemGridViewBinding): RecyclerView.ViewHolder(gridViewBinding.root) {
        fun bind(item: Item) {
            gridViewBinding.apply {
                tvItem.text = item.name
                tvItemPrice.text = item.price
//                item.extra?.let {
//                    tvShipping.visibility = View.VISIBLE
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemAdapter.ViewHolder {
        val view = ItemGridViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GridItemAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridItemAdapter.ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}