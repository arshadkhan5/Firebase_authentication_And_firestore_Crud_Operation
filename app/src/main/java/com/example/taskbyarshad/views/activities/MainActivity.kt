package com.example.taskbyarshad.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.taskbyarshad.R
import com.example.taskbyarshad.databinding.ActivityMainBinding
import com.example.taskbyarshad.views.fragments.AddUser
import com.example.taskbyarshad.views.fragments.Update

class MainActivity : AppCompatActivity() {
     private var binding : ActivityMainBinding ? = null
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }

    override fun onBackPressed() {
        val currentDestinationId = navController.currentDestination?.id
        Log.e("nav", "onBackPressed: $currentDestinationId ", )

        when (currentDestinationId) {
            R.id.update -> {
                // Handle navigation to DashboardFragment using the appropriate action
                val action = R.id.action_update_to_dashboard
                navController.navigate(action)
            }
            R.id.addUser -> {
                // Handle navigation to DashboardFragment using the appropriate action
                val action = R.id.action_addUser_to_dashboard
                navController.navigate(action)
            }
            else -> {
                // Show exit confirmation dialog or handle other navigation
                showExitConfirmationDialog()
            }
        }
    }


    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit App")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes") { _, _ ->
            // Perform any cleanup or save state if needed
            finishAffinity() // Close the app
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }








}