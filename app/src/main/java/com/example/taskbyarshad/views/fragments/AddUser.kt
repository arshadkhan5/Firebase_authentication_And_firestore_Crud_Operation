package com.example.taskbyarshad.views.fragments

import AdapterClass
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.taskbyarshad.FirestoreViewmodel
import com.example.taskbyarshad.R
import com.example.taskbyarshad.User
import com.example.taskbyarshad.databinding.FragmentAddUserBinding


class AddUser : Fragment() {

    private lateinit var myViewModel: FirestoreViewmodel

    private var binding : FragmentAddUserBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel = ViewModelProvider(this).get(FirestoreViewmodel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(layoutInflater)





            binding?.buttonAdd?.setOnClickListener {
            // Get user input from EditText fields
            val name =  binding?.editTextName?.text.toString()
            val city = context?.getString(R.string.lorem)

            // Add user to Firestore
            val user = User(name, city!!)
            myViewModel.addUserToFirestore(user)
                Toast.makeText(requireContext(),"Added Successfully ",Toast.LENGTH_SHORT).show()
            // Clear EditText fields
            binding?.editTextName?.text?.clear()
          binding?.editTextCity?.text?.clear()
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding =null
    }
}