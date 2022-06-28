package com.example.podcastgeist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Form_Login_Ingreso_Datos (var TipoGestion: Int = 1) : AppCompatActivity() {

    private lateinit var  txtCorreo : EditText
    private lateinit var  txtContrasena : EditText
    private lateinit var  btnLogin : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        val myIntent = intent
        val TipoGestionParam = myIntent.getStringExtra("TipoGestion")

        if (TipoGestionParam != null) {
            TipoGestion = TipoGestionParam.toInt()
        }
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        setContentView(R.layout.form_login_ingreso_datos)
        btnLogin = findViewById<Button>(R.id.btnLogin)

        if(TipoGestion == 1){
            btnLogin.text = "Ingresar"
            btnLogin.setOnClickListener { Login() }
        }
        else{
            btnLogin.setOnClickListener { SignUp() }
            btnLogin.text = "Registrar"
        }
    }

    private fun Login() {
        txtCorreo = findViewById(R.id.txtCorreo)
        txtContrasena = findViewById(R.id.txtContraseña)

        auth.signInWithEmailAndPassword(txtCorreo.text.toString(),txtContrasena.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    MostrarFormPrincipal(user)
                } else {
                    Toast.makeText(baseContext,
                        getString(R.string.msg_error_Login),
                        Toast.LENGTH_SHORT).show()
                    MostrarFormPrincipal(null)
                }
            }
    }

    private fun SignUp() {
        txtCorreo = findViewById(R.id.txtCorreo)
        txtContrasena = findViewById(R.id.txtContraseña)

        auth.createUserWithEmailAndPassword(txtCorreo.text.toString(),txtContrasena.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    MostrarFormPrincipal(user)
                } else {
                    Toast.makeText(baseContext,
                        getString(R.string.msg_error_SignUp),
                        Toast.LENGTH_SHORT).show()
                    MostrarFormPrincipal(null)
                }
            }
    }

    private fun MostrarFormPrincipal(user: FirebaseUser?) {
        if (user!=null) {
            val intent = Intent(this,Form_Principal_Podcast::class.java)
            startActivity(intent)
        }
    }

}