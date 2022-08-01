package com.example.podcastgeist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Form_Usuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_usuario)

        var btnLogOut : TextView = findViewById(R.id.btnLogOut)
        btnLogOut.setOnClickListener { LogOut() }

    }
    fun LogOut() {
        Firebase.auth.signOut()
        finish()
        val intent = Intent(this, Form_Principal::class.java)
        startActivity(intent)
    }
}
