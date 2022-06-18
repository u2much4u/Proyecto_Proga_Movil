package com.example.podcastgeist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Form_Login_Ingreso_Datos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_login_ingreso_datos)
    }

    fun validarLogin(view: View) {
        val intent = Intent(this,Form_Principal_Podcast::class.java)
        startActivity(intent)
    }
}