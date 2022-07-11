package com.example.podcastgeist

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.ImageButton
import com.example.podcastgeist.Form_Principal_Lista_Podcast.Companion.lstPodcast
import com.example.podcastgeist.model.Entidad_Podcast_Capitulo
import com.squareup.picasso.Picasso


class Variables_Globales {
    companion object {
        var isPlaying : Boolean = false
        lateinit  var appContext: Context
        var mediaPlayer : MediaPlayer? = null
        var IDPocadcastSeleccionado : Int? = 0
        var Entidad_Podcast_CapituloSeleccionado = Entidad_Podcast_Capitulo()
        var lstBotonesPlayPause : MutableList<ImageButton> = mutableListOf()


        fun ActualizarEscuchando(nombreVentana : String = "") {
            if (nombreVentana == "Form_Principal_Lista_Podcast") {
                Form_Principal_Lista_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == Entidad_Podcast_CapituloSeleccionado.IDPodcast }?.Nombre
                Form_Principal_Lista_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get() .load((lstPodcast.find { x -> x.ID == Entidad_Podcast_CapituloSeleccionado.IDPodcast }?.ImagenURL)).into(Form_Principal_Lista_Podcast.imagePodcastEscuchando)
            }

            if (nombreVentana == "Form_Principal_Podcast") {
                Form_Principal_Podcast.labelNombrePodcastEscuchando.text = lstPodcast.find { x -> x.ID == Entidad_Podcast_CapituloSeleccionado.IDPodcast }?.Nombre
                Form_Principal_Podcast.labelNombreCapituloEscuchando.text = this.Entidad_Podcast_CapituloSeleccionado.Nombre
                Picasso.get() .load((lstPodcast.find { x -> x.ID == Entidad_Podcast_CapituloSeleccionado.IDPodcast }?.ImagenURL)).into(Form_Principal_Podcast.imagePodcastEscuchando)
            }

            if(nombreVentana == "*"){
                ActualizarEscuchando("Form_Principal_Lista_Podcast")
                ActualizarEscuchando("Form_Principal_Podcast")
            }

            AsignarOnclicStop()
        }
        fun AsignarOnclicStop() {
            for (btn in lstBotonesPlayPause) {
                btn.setBackgroundDrawable(appContext.getResources()?.getDrawable(R.drawable.icono_pause))
                btn.setOnClickListener {
                    mediaPlayer!!.pause()
                    AsignarOnclicPlay()
                }
            }
        }
        fun AsignarOnclicPlay(){
            for (btn in lstBotonesPlayPause) {
                btn.setBackgroundDrawable(appContext.getResources()?.getDrawable(R.drawable.icono_reproducir))
                btn.setOnClickListener {
                    mediaPlayer!!.start()
                    AsignarOnclicStop()
                }
            }
        }
    }
}