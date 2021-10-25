package com.aryanwalia.firebaselearningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var sign_up_button : TextView
    private lateinit var login_button : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var email_field : EditText
    private lateinit var pass_field : EditText
    private lateinit var progress_bar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sign_up_button = findViewById(R.id.sign_up_option)
        login_button = findViewById(R.id.login_button)
        email_field = findViewById(R.id.email_field)
        pass_field = findViewById(R.id.pass_field)
        progress_bar = findViewById(R.id.progress_bar)
        mAuth = FirebaseAuth.getInstance()

        login_button.setOnClickListener {
            val email = email_field.text.toString().trim()
            val pass = pass_field.text.toString().trim()

            if(email.isEmpty()){
                email_field.error = "Email Required"
                email_field.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_field.error = "Valid Email Required"
                email_field.requestFocus()
                return@setOnClickListener
            }

            if(pass.isEmpty() || pass.length < 6){
                pass_field.error = "6 characters required"
                pass_field.requestFocus()
                return@setOnClickListener
            }

            login_user(email,pass)
        }


        sign_up_button.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }

    private fun login_user(email: String, pass: String) {
        progress_bar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email,pass)
            .addOnCompleteListener(this){ task ->
                progress_bar.visibility = View.GONE
                if(task.isSuccessful){
                    //success
                    val intent = Intent(this,HomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }else{
                    //failure
                    task.exception?.message?.let{
                        toast(it)
                    }
                }
            }
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }
}