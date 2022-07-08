package com.example.podcastgeist

import android.animation.ObjectAnimator
import android.widget.ProgressBar
import com.example.podcastgeist.Form_Principal_Lista_Podcast.Companion.lstPodcast
import com.example.podcastgeist.model.Entidad_Podcast_Capitulo
import com.squareup.picasso.Picasso

class Variables_Globales {
    companion object {
        var IDPocadcastSeleccionado : Int? = 0
        var Entidad_Podcast_CapituloSeleccionado = Entidad_Podcast_Capitulo()
        var isPlaying : Boolean = false

        fun ActualizarEscuchando(nombreVentana : String = "") {
            if (nombreVentana == "Form_Principal_Lista_Podcast") {
                Form_Principal_Lista_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.Nombre
                Form_Principal_Lista_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get() .load((lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.ImagenURL)).into(Form_Principal_Lista_Podcast.imagePodcastEscuchando)
            }

            if (nombreVentana == "Form_Principal_Podcast") {
                Form_Principal_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.Nombre
                Form_Principal_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get() .load((lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.ImagenURL)).into(Form_Principal_Podcast.imagePodcastEscuchando)
            }

            if(nombreVentana == "*"){
                Form_Principal_Lista_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.Nombre
                Form_Principal_Lista_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get() .load((lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.ImagenURL)).into(Form_Principal_Lista_Podcast.imagePodcastEscuchando)

                Form_Principal_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.Nombre
                Form_Principal_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get().load((lstPodcast.find { x -> x.ID == IDPocadcastSeleccionado }?.ImagenURL)).into(Form_Principal_Podcast.imagePodcastEscuchando)
            }
        }
    }



}