package com.example.praktikum_psi_final

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum_psi_final.databinding.ItemViewProductBinding
import com.example.praktikum_psi_final.repository.ModelBarang
import com.squareup.picasso.Picasso


class ProductByCategoryAdapter : RecyclerView.Adapter<ProductByCategoryAdapter.ViewHolder>() {
    var dataAdapter = listOf<ModelBarang>()
    fun setData(data: List<ModelBarang>) {
        dataAdapter = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemViewProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initData(modelBarang: ModelBarang) {
            binding.apply {
                tvItemTitle.text = modelBarang.namaBarang
                tvItemPrice.text = modelBarang.hargaBarang.toString()
                Picasso.get().load(modelBarang.fotoProduk).into(ivItemPhoto)
                ivItemPhoto.setOnClickListener {
//                    val bundle = Bundle()
//                    bundle.putString("idBarang", modelBarang.idBarang)
//                    it.findNavController()
//                        .navigate(R.id.action_productByCategoryFragment_detailFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val data = dataAdapter[position]
        holder.initData(data)
    }

    override fun getItemCount(): Int {
        return dataAdapter.size
    }

}