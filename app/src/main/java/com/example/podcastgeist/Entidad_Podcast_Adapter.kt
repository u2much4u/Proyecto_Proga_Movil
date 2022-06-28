package com.example.podcastgeist

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class Entidad_Podcast_Adapter : RecyclerView.Adapter<Entidad_Podcast_Adapter.ViewHolder>(){

    var lstPodcast = Form_Principal_Podcast.lstPodcast
    var Nombres : MutableList<String> = mutableListOf()
    var Descripciones : MutableList<String> = mutableListOf()
    var Imagenes : MutableList<String> = mutableListOf()

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itemNombre : TextView
        var itemDescripcion: TextView
        var itemImagen: ImageView

        init{
            itemNombre = itemView.findViewById(R.id.labelPodcastNombre)
            itemDescripcion = itemView.findViewById(R.id.labelPodcastDescripcion)
            itemImagen = itemView.findViewById(R.id.podcast_cover)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        for (entPodcast in lstPodcast){
            Nombres.add(entPodcast.Nombre.toString())
            Descripciones.add(entPodcast.Descripcion.toString())
            Imagenes.add(entPodcast.ImagenURL.toString())
        }

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.entidad_podcast_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemNombre.text = Nombres[i]
        holder.itemDescripcion.text = Descripciones[i]
        Picasso.get().load((Imagenes[i]) as String?).into(holder.itemImagen)
    }

    override fun getItemCount(): Int {
        return lstPodcast.count()
    }
}