package com.sachin.workindiaassessment.ui.adapters

import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.workindiaassessment.databinding.ItemGridViewBinding
import com.sachin.workindiaassessment.databinding.ItemLinearViewBinding
import com.sachin.workindiaassessment.models.Item

class LinearItemAdapter(
    private val itemsList: List<Item>
) : RecyclerView.Adapter<LinearItemAdapter.ViewHolder>(){

    class ViewHolder(private val linearViewBinding: ItemLinearViewBinding): RecyclerView.ViewHolder(linearViewBinding.root) {
        fun bind(item: Item) {
            linearViewBinding.apply {
                tvItem.text = item.name
                tvItemPrice.text = item.price
                item.extra?.let {
                    tvShipping.visibility = VISIBLE
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLinearViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = itemsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}