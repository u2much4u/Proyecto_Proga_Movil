package com.example.podcastgeist.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.podcastgeist.Form_Principal_Lista_Podcast
import com.example.podcastgeist.Form_Principal_Podcast
import com.example.podcastgeist.R
import com.example.podcastgeist.Variables_Globales
import com.example.podcastgeist.model.Entidad_Podcast
import com.squareup.picasso.Picasso


class Entidad_Podcast_Adapter : RecyclerView.Adapter<Entidad_Podcast_Adapter.ViewHolder>(){

    var lstPodcast = Form_Principal_Lista_Podcast.lstPodcast
    var lstPodcastAux: MutableList<Entidad_Podcast> = mutableListOf()

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itemNombre : TextView
        var itemDescripcion: TextView
        var itemImagen: ImageView
        var itemCardview : RelativeLayout

        init{
            itemNombre = itemView.findViewById(R.id.labelNombrePodcast)
            itemDescripcion = itemView.findViewById(R.id.labelPodcastDescripcion)
            itemImagen = itemView.findViewById(R.id.podcast_cover)
            itemCardview = itemView.findViewById(R.id.Relative_Layout_PanelContenedor)
        }

        fun onclicka(i: Int) {
            itemCardview.setOnClickListener { v ->
                val context = v.context
                val intent = Intent(context, Form_Principal_Podcast::class.java)
                Variables_Globales.IDPocadcastSeleccionado = i
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val entPodcastCardView = LayoutInflater.from(parent.context).inflate(R.layout.entidad_podcast_layout, parent, false)
        if(Variables_Globales.IDCategoriaSeleccioada != "*"){
            lstPodcastAux.clear()
            for (entidadPodcastItem: Entidad_Podcast in lstPodcast) {
                if(entidadPodcastItem.IDCategoria == Variables_Globales.IDCategoriaSeleccioada)
                    lstPodcastAux.add(entidadPodcastItem)
            }
        }
        return ViewHolder(entPodcastCardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        if(Variables_Globales.IDCategoriaSeleccioada == "*"){
            holder.itemNombre.text = lstPodcast.elementAt(i).Nombre
            holder.itemDescripcion.text = lstPodcast.elementAt(i).Descripcion
            Picasso.get().load((lstPodcast.elementAt(i).ImagenURL)).into(holder.itemImagen)
            holder.itemCardview.setOnClickListener{
                holder.onclicka(lstPodcast.elementAt(i).ID!!)
            }
        }
        else {
            holder.itemNombre.text = lstPodcastAux.elementAt(i).Nombre
            holder.itemDescripcion.text = lstPodcastAux.elementAt(i).Descripcion
            Picasso.get().load((lstPodcastAux.elementAt(i).ImagenURL)).into(holder.itemImagen)
            holder.itemCardview.setOnClickListener{
                holder.onclicka(lstPodcastAux.elementAt(i).ID!!)
            }
        }
    }

    override fun getItemCount(): Int {
        if(Variables_Globales.IDCategoriaSeleccioada == "*")
            return lstPodcast.count()
        else{
            var contador:Int = 0
            for (entidadPodcastItem: Entidad_Podcast in lstPodcast) {
                if(entidadPodcastItem.IDCategoria == Variables_Globales.IDCategoriaSeleccioada)
                    contador++
            }
            return contador
        }
    }
}