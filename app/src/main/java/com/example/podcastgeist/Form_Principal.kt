package com.example.podcastgeist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Form_Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_principal)
    }

    fun login(view: View) {
        val intent = Intent(this,Form_Login_Ingreso_Datos::class.java)
        startActivity(intent)
    }

}