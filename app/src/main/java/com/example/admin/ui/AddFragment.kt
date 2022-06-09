package com.example.admin.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.admin.R
import com.example.admin.data.ModelBarang
import com.example.admin.data.ViewModel
import com.example.admin.databinding.FragmentAddBinding
import com.example.admin.util.LoadingHelper
import com.example.admin.util.log
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.util.*

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var selectedPhotoUri: Uri? = null
    var modelBarangGlobal: ModelBarang = ModelBarang("", "", 0, "", "", "")
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekData()
        binding.apply {
            IDimageProduk.setOnClickListener {
                getImage()
            }
            IDButtonSimpan.setOnClickListener {
                when (binding.IDButtonSimpan.text) {
                    "Save" -> simpanProduk()
                    "Update" -> updateProduk()
                }
            }
            btnHapus.setOnClickListener(){
                deleteProduk()
            }
        }
    }

    fun simpanProduk() {
        log("Proses simpan")
        if (selectedPhotoUri == null) {
            Toast.makeText(requireContext(), "masukan foto produk", Toast.LENGTH_SHORT).show()
        } else {
            val nama = binding.IDEditNama.text.toString()
            val kategori = binding.IDEditKategori.text.toString()
            val harga = binding.idEditHarga.text.toString().toInt()
            val deskripsi = binding.IDEditDeskripsi.text.toString()

            if (nama == "" && kategori == "" && harga == 0 && deskripsi == "") {
                Toast.makeText(requireContext(), "lengkapi data produk", Toast.LENGTH_SHORT).show()
            } else {
                uploadImage(selectedPhotoUri).observe(viewLifecycleOwner, {
                    val data = ModelBarang("id", nama, harga, kategori, deskripsi, it)
                    mViewModel.setDataBarang(
                        requireContext(), data, findNavController().navigate(
                            R.id.action_addFragment_to_homeFragment
                        )
                    )
                })
            }

        }
    }

    fun updateProduk() {
        log("proses update")
        val nama = binding.IDEditNama.text.toString()
        val harga = binding.idEditHarga.text.toString().toInt()
        val kategori = binding.IDEditKategori.text.toString()
        val deskripsi = binding.IDEditDeskripsi.text.toString()

        if (selectedPhotoUri==null){

            mViewModel.updtaeBarang(
                ModelBarang(
                    modelBarangGlobal.idBarang,
                    nama,
                    harga,
                    kategori,
                    deskripsi,
                    modelBarangGlobal.fotoProduk
                ), findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            )
        }else{
            uploadImage(selectedPhotoUri).observe(viewLifecycleOwner,{fotoBaru->
                mViewModel.updtaeBarang(
                    ModelBarang(
                        modelBarangGlobal.idBarang,
                        nama,
                        harga,
                        kategori,
                        deskripsi,
                       fotoBaru
                    ), findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                )

            })
        }

    }

    fun deleteProduk(){
        mViewModel.deleteBarang(
            modelBarangGlobal, findNavController().navigate(R.id.action_addFragment_to_homeFragment),requireContext()
        )
    }

    fun getImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 100)
    }

    private fun uploadImage(imageUri: Uri?): MutableLiveData<String> {
        val loading = LoadingHelper(requireContext())
        loading.show()
        log("coba upload")
        val filename = UUID.randomUUID().toString()
        val storage = FirebaseStorage.getInstance().getReference("/PKL/$filename")
        var uri = MutableLiveData<String>()
        storage.putFile(imageUri!!).addOnSuccessListener {
            log("berhasil upload")
            storage.downloadUrl.addOnSuccessListener {
                uri.value = it.toString()
                loading.dismiss()
            }.addOnFailureListener {
                log(it.localizedMessage)
                loading.dismiss()
            }

        }
        return uri

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100 && data != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(
                requireContext().contentResolver,
                selectedPhotoUri
            )
            binding.IDimageProduk.setImageBitmap(bitmap)
        }
    }

    fun cekData() {
        val idBarang = arguments?.getString("idBarang")
        if (idBarang != null) {
            binding.btnHapus.visibility = View.VISIBLE
            binding.IDButtonSimpan.text = "Update"
            mViewModel.getDataBarang().observe(viewLifecycleOwner, { result ->
                val data = result.filter {
                    it.idBarang == idBarang
                }
                modelBarangGlobal = data[0]
                log(data)
                binding.IDEditNama.setText(data[0].namaBarang)
                binding.IDEditKategori.setText(data[0].kategori)
                binding.idEditHarga.setText(data[0].hargaBarang.toString())
                binding.IDEditDeskripsi.setText(data[0].deskripsi)
                Picasso.get().load(data[0].fotoProduk).into(binding.IDimageProduk)
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}