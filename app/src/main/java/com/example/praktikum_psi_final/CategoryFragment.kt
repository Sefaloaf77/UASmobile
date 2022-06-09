package com.example.praktikum_psi_final

import Product
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum_psi_final.repository.ViewModel


class CategoryFragment : Fragment(), Product {

    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }
    private lateinit var productByCategoryFragment: ProductByCategoryFragment
    private var kategori: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btKaos = view.findViewById<Button>(R.id.bt_kaos)
        var btAksesoris = view.findViewById<Button>(R.id.bt_aksesoris)
        var btKemeja = view.findViewById<Button>(R.id.bt_kemeja)
        var btCelana = view.findViewById<Button>(R.id.bt_celana)

        var txtKaos = btKaos.text.toString()
        var txtAksesoris = btAksesoris.text.toString()
        var txtKemeja = btKemeja.text.toString()
        var txtCelana = btCelana.text.toString()
        btKaos.setOnClickListener {
            productByCategoryFragment = ProductByCategoryFragment()
            productByCategoryFragment.getCategory(txtKaos)
            replaceFragment(productByCategoryFragment)
        }
        btAksesoris.setOnClickListener {
            productByCategoryFragment = ProductByCategoryFragment()
            productByCategoryFragment.getCategory(txtAksesoris)
            replaceFragment(productByCategoryFragment)
        }

        btKemeja.setOnClickListener {
            productByCategoryFragment = ProductByCategoryFragment()
            productByCategoryFragment.getCategory(txtKemeja)
            replaceFragment(productByCategoryFragment)
        }
        btCelana.setOnClickListener {
            productByCategoryFragment = ProductByCategoryFragment()
            productByCategoryFragment.getCategory(txtCelana)
            replaceFragment(productByCategoryFragment)

        }
    }


    fun replaceFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main, fragment)
        transaction.commit()
    }

    override fun getCategory(category: String) {
        TODO("Not yet implemented")
    }


}