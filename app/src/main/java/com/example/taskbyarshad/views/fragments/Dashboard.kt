package com.example.taskbyarshad.views.fragments

import AdapterClass
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskbyarshad.FirestoreViewmodel
import com.example.taskbyarshad.R
import com.example.taskbyarshad.databinding.FragmentDashboardBinding


class Dashboard : Fragment() {

    private var binding : FragmentDashboardBinding ? = null

    private lateinit var myViewModel: FirestoreViewmodel
    private lateinit var userAdapter: AdapterClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)




        userAdapter = AdapterClass( onDeleteClickListener = { user ->
            // Handle the delete button click

                val builder = AlertDialog.Builder(requireContext())

                // Set the alert dialog title and message
                builder.setTitle("Delete Confirmation")
                    .setMessage("Are you sure you want to delete this item?")

                // Set up the buttons
                builder.setPositiveButton("Yes") { _, _ ->
                    // Delete the item
                    myViewModel.deleteUserFromFirestore(user)
                }

                builder.setNegativeButton("No") { dialog, _ ->
                    // Dismiss the dialog if "No" is clicked
                    dialog.dismiss()
                }

                // Create and show the dialog
                val dialog = builder.create()
                dialog.show()



        },
            onUpdateClickListener = {user ->

                findNavController().navigate(R.id.action_dashboard_to_update,Bundle().apply {
                    putString("type","edit")
                    putParcelable("note",user)
                })

            }
        )

        binding?.recycleView?.adapter = userAdapter
        binding?.recycleView?.layoutManager = LinearLayoutManager((context))

        myViewModel = ViewModelProvider(this).get(FirestoreViewmodel::class.java)

        // Fetch data from Firestore when the Fragment is created
        myViewModel.fetchDataFromFirestore()
        // Observe the LiveData in the ViewModel
        myViewModel.userData.observe( viewLifecycleOwner, Observer {
            userAdapter.submitList(it)
        })

        binding?.buttonAdd?.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_addUser)

        }
        return binding?.root
    }

}