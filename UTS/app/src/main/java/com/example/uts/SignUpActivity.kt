package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uts.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private val database = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
//        binding.textView.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }

        binding.button.setOnClickListener {
            val username = binding.usernameEt.text.toString()
            val pass = binding.passET.text.toString()
            val git = binding.gitHubUserNameEt.text.toString()
            val nim = binding.nikEt.text.toString()
            val email = binding.emailEt.text.toString()

            if (pass.isNotEmpty() && email.isNotEmpty()) {
                if (pass != null) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val dataUser = hashMapOf(
                                "username" to username,
                                "GitHub Name" to git,
                                "NIM" to nim,
                                "Email" to email

                            )
                            database.collection("dbs").document("9RdzYCym3Rp1jYrhxdL4")
                                .set(dataUser)

                                .addOnFailureListener{
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                }

                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Kotak tidak boleh kosong!!!", Toast.LENGTH_SHORT).show()
            }
            binding.button.setOnClickListener {
                val login = Intent(this, LoginActivity::class.java)
                startActivity(login)
            }
        }
    }
}