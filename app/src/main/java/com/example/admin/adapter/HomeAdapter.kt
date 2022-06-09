package com.example.admin.adapter

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.admin.R
import com.example.admin.data.ModelBarang
import com.example.admin.databinding.ItemHomeBinding
import com.squareup.picasso.Picasso

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var dataAdapter = listOf<ModelBarang>()
    fun setData(data: List<ModelBarang>) {
        dataAdapter = data
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun initData(modelBarang: ModelBarang) {
        binding.apply {
            IDNamaBarang.text=modelBarang.namaBarang
            IDHargaBarang.text=modelBarang.hargaBarang.toString()
            Picasso.get().load(modelBarang.fotoProduk).into(IDItemBarang)
            IDItemBarang.setOnClickListener{
                val bundle=Bundle()
                bundle.putString("idBarang", modelBarang.idBarang)
                it.findNavController().navigate(R.id.action_homeFragment_to_addFragment, bundle)

            }
        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
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