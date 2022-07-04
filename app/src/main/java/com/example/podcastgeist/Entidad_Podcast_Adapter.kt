package com.example.podcastgeist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class Entidad_Podcast_Adapter : RecyclerView.Adapter<Entidad_Podcast_Adapter.ViewHolder>(){


    var lstPodcast = Form_Principal_Lista_Podcast.lstPodcast
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itemNombre : TextView
        var itemDescripcion: TextView
        var itemImagen: ImageView
        var itemCardview : RelativeLayout

        init{
            itemNombre = itemView.findViewById(R.id.labelPodcastNombre)
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
        return ViewHolder(entPodcastCardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemNombre.text = lstPodcast.elementAt(i).Nombre
        holder.itemDescripcion.text = lstPodcast.elementAt(i).Descripcion
        Picasso.get().load((lstPodcast.elementAt(i).ImagenURL)).into(holder.itemImagen)
        holder.itemCardview.setOnClickListener{
            holder.onclicka(lstPodcast.elementAt(i).ID!!)
        }


    }

    override fun getItemCount(): Int {
        return lstPodcast.count()
    }

}