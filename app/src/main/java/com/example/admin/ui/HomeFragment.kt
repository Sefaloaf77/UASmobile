package com.example.admin.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.R
import com.example.admin.adapter.HomeAdapter
import com.example.admin.data.ViewModel
import com.example.admin.databinding.FragmentHomeBinding
import com.example.admin.databinding.FragmentLoginBinding
import com.example.admin.util.data
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        binding.IDButtonAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        binding.IDHomeLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }
    }


    fun init(){

        mViewModel.getDataBarang().observe(viewLifecycleOwner, Observer {
            val adapter = HomeAdapter()
            binding.IDHomeRecyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.IDHomeRecyclerview.adapter = adapter
            adapter.setData(it)
        })
    }





    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }




}