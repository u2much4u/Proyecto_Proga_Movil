package com.example.podcastgeist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Form_Principal_Podcast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_principal_podcast)

        var itemNombre : TextView = findViewById(R.id.labelPodcastNombre)
        var itemDescripción : TextView = findViewById(R.id.labelPodcastDescripcion)
        var itemCover : ImageView = findViewById(R.id.podcast_cover)

        var entPodcast : Entidad_Podcast? = Form_Principal_Lista_Podcast.Companion.lstPodcast.
        find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }

        if (entPodcast != null) {
            itemNombre.text = entPodcast.Nombre
            itemDescripción.text = entPodcast.Descripcion
            Picasso.get().load((entPodcast.ImagenURL)).into(itemCover)
        }
    }
}