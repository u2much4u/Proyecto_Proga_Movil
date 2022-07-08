package com.example.podcastgeist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.podcastgeist.adapter.Entidad_Capitulo_Adapter
import com.example.podcastgeist.model.Entidad_Podcast
import com.squareup.picasso.Picasso

class Form_Principal_Podcast : AppCompatActivity() {

    companion object {
        lateinit var imagePodcastEscuchando: ImageView
        lateinit var labelNombrePodcastEscuchando: TextView
        lateinit var labelNombreCapituloEscuchando: TextView
        lateinit var btnReproducirEscuchando: ImageButton
        lateinit var btnPausarEscuchando: ImageButton
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_principal_podcast)

        var itemNombre : TextView = findViewById(R.id.labelNombrePodcast)
        var itemDescripción : TextView = findViewById(R.id.labelPodcastDescripcion)
        var itemCover : ImageView = findViewById(R.id.podcast_cover)

        var entPodcast : Entidad_Podcast? = Form_Principal_Lista_Podcast.Companion.lstPodcast.
        find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }

        if (entPodcast != null) {
            itemNombre.text = entPodcast.Nombre
            itemDescripción.text = entPodcast.Descripcion
            Picasso.get().load((entPodcast.ImagenURL)).into(itemCover)
        }

        CargarControlesEscuchando()
        val recyclerView = findViewById<RecyclerView>(R.id.RecicladorCapitulos)
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        recyclerView.setAdapter(Entidad_Capitulo_Adapter())
        recyclerView.adapter?.notifyDataSetChanged()

        if(Variables_Globales.isPlaying){Variables_Globales.ActualizarEscuchando("Form_Principal_Podcast")}
    }

    private fun CargarControlesEscuchando(){
        imagePodcastEscuchando =findViewById<ImageView>(R.id.imagenPodcastActual)
        labelNombrePodcastEscuchando =findViewById<TextView>(R.id.labelNombrePodcastActual)
        labelNombreCapituloEscuchando =findViewById<TextView>(R.id.labelNombreCapituloActual)
        btnReproducirEscuchando =findViewById<ImageButton>(R.id.btnPlayEscuchando)
        btnPausarEscuchando =findViewById<ImageButton>(R.id.btnPauseEscuchando)
    }
}