package com.example.taskbyarshad.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.taskbyarshad.R
import com.example.taskbyarshad.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        auth = FirebaseAuth.getInstance()
// Get the text entered by the user


        binding?.loginButton?.setOnClickListener {


            val email: String = binding?.emailEt?.text.toString()
            val password: String = binding?.emailEt?.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registration successful
                            val user = auth.currentUser
                            if (user?.email==email && user.isEmailVerified){
                                Log.e("user", "onCreate:  $user", )
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }


                            // You can now do something with the user information
                        } else {
                            // Registration failed
                            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                            Log.e("user", "exception: ${task.exception.toString()}", )
                            Toast.makeText(baseContext, "Registration failed . Sig Up ", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }







        binding?.textViewCreateAccount?.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }

        binding?.forgotPassword?.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser !=null){
            startActivity(Intent(this , MainActivity::class.java))
            finish()
        }
    }



}