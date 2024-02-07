package com.example.taskbyarshad.views.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskbyarshad.R
import com.google.firebase.auth.FirebaseAuth


class ForgotActivity : AppCompatActivity() {
    //Declaration
    var btnReset: Button? = null
    var btnBack:Button? = null
    var edtEmail: EditText? = null
    var progressBar: ProgressBar? = null
    var mAuth: FirebaseAuth? = null
    var strEmail: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        btnBack = findViewById(R.id.btnForgotPasswordBack)
        btnReset = findViewById(R.id.btnReset)
        edtEmail = findViewById(R.id.edtForgotPasswordEmail)
        progressBar = findViewById(R.id.forgetPasswordProgressbar)
        mAuth = FirebaseAuth.getInstance()

        //Reset Button Listener
        btnReset!!.setOnClickListener {
                strEmail = edtEmail!!.text.toString().trim { it <= ' ' }
                if (!TextUtils.isEmpty(strEmail)) {
                    ResetPassword()
                } else {
                    edtEmail!!.error = "Email field can't be empty"
                }
            }



        //Back Button Code
        btnBack?.setOnClickListener{
                onBackPressed()
            }
    }


    private fun ResetPassword() {
        progressBar!!.visibility = View.VISIBLE
        btnReset!!.visibility = View.INVISIBLE
        mAuth!!.sendPasswordResetEmail(strEmail!!)
            .addOnSuccessListener {
                Toast.makeText(
                    this@ForgotActivity,
                    "Reset Password link has been sent to your registered Email",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@ForgotActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@ForgotActivity, "Error :- " + e.message, Toast.LENGTH_SHORT)
                    .show()
                progressBar!!.visibility = View.INVISIBLE
                btnReset!!.visibility = View.VISIBLE
            }
    }
}







