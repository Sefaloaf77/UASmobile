package com.example.admin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.admin.R
import com.example.admin.data.ViewModel
import com.example.admin.databinding.FragmentLoginBinding
import com.example.admin.util.log

import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val mViewModel by lazy {
        ViewModelProvider(this).get(ViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cekLogin()
        binding.IDLogin.setOnClickListener {
            val user = binding.IDUsername.text.toString()
            val pass = binding.IDPass.text.toString()
            FirebaseAuth.getInstance().signInWithEmailAndPassword(user, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    log("Berhasil")
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    log("failed")
                    Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()

            }
        }
    }
    fun cekLogin(){
        val user = FirebaseAuth.getInstance().currentUser
        if (user!=null){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}