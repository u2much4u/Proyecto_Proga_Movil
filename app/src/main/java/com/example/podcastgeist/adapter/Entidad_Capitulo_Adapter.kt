package com.example.podcastgeist.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.podcastgeist.Form_Principal_Lista_Podcast
import com.example.podcastgeist.R
import com.example.podcastgeist.Variables_Globales


class Entidad_Capitulo_Adapter : RecyclerView.Adapter<Entidad_Capitulo_Adapter.ViewHolder>(){

    var lstPodcast = Form_Principal_Lista_Podcast.lstPodcast
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itembtnReproducir : ImageButton
        var itemNombreCapitulo: TextView
        var itemDescripcionCapitulo: TextView

        init{
            itembtnReproducir = itemView.findViewById(R.id.btnReproducirCapitulo)
            itemNombreCapitulo = itemView.findViewById(R.id.labelNombreCapitulo)
            itemDescripcionCapitulo = itemView.findViewById(R.id.labelDescripcionCapitulo)

        }

        fun onclicka(i: Int) {
            Variables_Globales.Entidad_Podcast_CapituloSeleccionado = lstPodcast.find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado
            }?.lstCapitulos?.find{ z -> z.IDCapitulo == i }!!
            Variables_Globales.ActualizarEscuchando("*")
            Variables_Globales.mediaPlayer = MediaPlayer()
            Variables_Globales.mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
            Variables_Globales.mediaPlayer!!.setDataSource(Variables_Globales.Entidad_Podcast_CapituloSeleccionado.URL)
            Variables_Globales.mediaPlayer!!.prepare()
            Variables_Globales.mediaPlayer!!.start()
            Variables_Globales.isPlaying = true
            Variables_Globales.AsignarOnclicStop()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val entCapituloCardView = LayoutInflater.from(parent.context).inflate(R.layout.entidad_capitulo_layout, parent, false)
        return ViewHolder(entCapituloCardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemNombreCapitulo.text = lstPodcast.find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }?.lstCapitulos?.elementAt(i)?.Nombre
        holder.itemDescripcionCapitulo.text = lstPodcast.find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }?.lstCapitulos?.elementAt(i)?.Descripcion
        holder.itembtnReproducir.setOnClickListener{
            holder.onclicka(lstPodcast.find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }?.lstCapitulos?.elementAt(i)?.IDCapitulo!!)
        }
    }

    override fun getItemCount(): Int {
        return lstPodcast.find { x -> x.ID == Variables_Globales.IDPocadcastSeleccionado }?.lstCapitulos!!.count()
    }

}