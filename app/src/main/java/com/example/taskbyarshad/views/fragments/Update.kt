package com.example.taskbyarshad.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskbyarshad.FirestoreViewmodel
import com.example.taskbyarshad.R
import com.example.taskbyarshad.User
import com.example.taskbyarshad.databinding.FragmentUpdateBinding


class Update : Fragment() {

    private lateinit var myViewModel: FirestoreViewmodel
    private var binding :FragmentUpdateBinding ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myViewModel = ViewModelProvider(this).get(FirestoreViewmodel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(layoutInflater)

        var objNote = arguments?.getParcelable<User>("note")
        binding?.editTextName?.setText(objNote?.name)
        binding?.editTextCity?.setText(objNote?.info)

        binding?.buttonAdd?.setOnClickListener {

            val updatedUser = User(
                binding?.editTextName?.text.toString(),
                binding?.editTextCity?.text.toString()
            )
            myViewModel.updateUserInFirestore(updatedUser)
            Toast.makeText(requireContext(),"Updated Successfully ", Toast.LENGTH_SHORT).show()


        }

        return binding?.root
    }


}