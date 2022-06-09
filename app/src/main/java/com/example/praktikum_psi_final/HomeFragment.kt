package com.example.praktikum_psi_final

import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum_psi_final.repository.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    private var rvListProduct: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        mViewModel.getDataBarang().observe(viewLifecycleOwner, Observer {
            val recycler = itemView.findViewById<RecyclerView>(R.id.rv_listProduct)
            val adapter = ProductByCategoryAdapter()
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = adapter
            adapter.setData(it)
        })
    }


}