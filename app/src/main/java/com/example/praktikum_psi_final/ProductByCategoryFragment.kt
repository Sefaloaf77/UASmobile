package com.example.praktikum_psi_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.praktikum_psi_final.databinding.FragmentProductByCategoryBinding
import com.example.praktikum_psi_final.repository.ViewModel

class ProductByCategoryFragment : Fragment() {
    private var _binding: FragmentProductByCategoryBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    private lateinit var category: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentProductByCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        init()
    }

    fun init(){
        mViewModel.findProductByCetegory(category).observe(viewLifecycleOwner, Observer {
            val adapter = ProductByCategoryAdapter()
            binding.productRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.productRecyclerview.adapter = adapter
            adapter.setData(it)
        })

    }

    fun getCategory(category: String) {
        this.category = category
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }

}