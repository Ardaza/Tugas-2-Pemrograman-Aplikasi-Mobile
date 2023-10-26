package com.example.uts.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.uts.LoginActivity
import com.example.uts.R
import com.example.uts.databinding.ActivityLoginBinding
import com.example.uts.databinding.FragmentProfilBinding
import com.example.uts.home.MyAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import org.w3c.dom.Text
import java.text.FieldPosition

class ProfilFragment : Fragment() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val database = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout. fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var propil = view.findViewById<TextView>(R.id.pp)
        var username = view.findViewById<TextView>(R.id.text_username)
        var gitHub = view.findViewById<TextView>(R.id.text_gitHub_username)
        var nim = view.findViewById<TextView>(R.id.text_NIM)
        var email = view.findViewById<TextView>(R.id.text_email)

        database.collection("dbs").document("9RdzYCym3Rp1jYrhxdL4")
            .get()
            .addOnCompleteListener { document ->
                if (document != null) {
                    var p = document.result.get("username").toString()
                    propil.text = p[0].toString()
                    username.text = document.result.get("username").toString()
                    gitHub.text = document.result.get("GitHub Name").toString()
                    nim.text = document.result.get("NIM").toString()
                    email.text = document.result.get("Email").toString()
                }
            }

        val tombol = view?.findViewById<Button>(R.id.logout_button)
        if (tombol != null) {
            tombol.setOnClickListener{
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }
    }
}