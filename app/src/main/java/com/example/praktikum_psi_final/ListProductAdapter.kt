package com.example.praktikum_psi_final

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListProductAdapter(private val listProduct: ArrayList<MainProduct>) :
    RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view_product, viewGroup, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, title, price) = listProduct[position]
        holder.ivItemPhoto.setImageResource(photo)
        holder.tvItemTitle.text = title
        holder.tvItemPrice.text = price.toString()
    }

    override fun getItemCount(): Int = listProduct.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivItemPhoto: ImageView = itemView.findViewById(R.id.iv_item_photo)
        var tvItemTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        var tvItemPrice: TextView = itemView.findViewById(R.id.tv_item_price)
    }

}