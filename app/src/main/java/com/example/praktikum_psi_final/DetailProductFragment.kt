package com.example.praktikum_psi_final

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum_psi_final.databinding.FragmentDetailProductBinding
import com.example.praktikum_psi_final.repository.ModelBarang
import com.example.praktikum_psi_final.repository.ViewModel
import com.squareup.picasso.Picasso

class DetailProductFragment : Fragment() {
    private var _binding: FragmentDetailProductBinding? = null
    private val binding get() = _binding!!
    var modelBarangGlobal: ModelBarang = ModelBarang("", "", 0, "", "", "")
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekData()
        binding.apply {
            ivFotoBarang.setOnClickListener {
                getImage()
            }
        }
    }

    private fun getImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }

    fun cekData() {
        val idBarang = arguments?.getString("idBarang")
        if (idBarang != null) {
            mViewModel.getDataBarang().observe(viewLifecycleOwner, { result ->
                val data = result.filter {
                    it.idBarang == idBarang
                }
                modelBarangGlobal = data[0]
                binding.tvNamaBarang.setText(data[0].namaBarang)
                binding.tvDeskripsi.setText(data[0].deskripsi)
                binding.tvHargaBarang.setText(data[0].hargaBarang)
                Picasso.get().load(data[0].fotoProduk).into(binding.ivFotoBarang)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}