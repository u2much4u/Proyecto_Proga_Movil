
package com.example.podcastgeist

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/*private lateinit var  btPhone : Button*/
private lateinit var btEmail: Button
private lateinit var btWhatsapp: Button
private lateinit var btWeb: Button

class Form_Soporte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_soporte)

        btEmail = findViewById(R.id.bt_email)
        btWhatsapp = findViewById(R.id.btnWhatsapp)
        btWeb = findViewById(R.id.bt_web)

        btEmail.setOnClickListener { escribirCorreo() }
        btWhatsapp.setOnClickListener { enviarWhatsapp() }
        btWeb.setOnClickListener { verWeb() }

        /* btPhone.setOnClickListener{ LlamarLugar() }*/
        /*btPhone = findViewById(R.id.bt_phone)*/
    }

    private fun escribirCorreo() {
        val para = "podcastg@gmail.com"
        if (para.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(para))
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.msg_saludos))
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.msg_mensaje_correo))
            startActivity(intent)
        }
    }

    private fun enviarWhatsapp() {
        val telefono = "1155"
        if (telefono.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = "whatsapp ://send?phone=506$telefono&text=" +
                    intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(uri)
            Variables_Globales.appContext.startActivity(intent)
        }
    }

    private fun verWeb() {
        val Sitio = "mytuner-radio.com/es/podcast/pais/top-costa-rica"
        if (Sitio.isNotEmpty()) {
            val uri = Uri.parse("http://$Sitio")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            Variables_Globales.appContext.startActivity(intent)
        }
    }
}