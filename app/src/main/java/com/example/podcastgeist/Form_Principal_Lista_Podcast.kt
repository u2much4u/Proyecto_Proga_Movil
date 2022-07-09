package com.example.podcastgeist

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.podcastgeist.adapter.Entidad_Podcast_Adapter
import com.example.podcastgeist.model.Entidad_Podcast
import com.example.podcastgeist.model.Entidad_Podcast_Capitulo
import com.google.firebase.firestore.FirebaseFirestore

class Form_Principal_Lista_Podcast : AppCompatActivity() {

    private var dbFirestore = FirebaseFirestore.getInstance()
    companion object {
        lateinit var imagePodcastEscuchando: ImageView
        lateinit var labelNombrePodcastEscuchando: TextView
        lateinit var labelNombreCapituloEscuchando: TextView
        var lstPodcast: MutableList<Entidad_Podcast> = mutableListOf()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Variables_Globales.appContext = applicationContext
        CargarPodcast()
        CargarPodcastCapitulos()
        println("----------------CANTIDAD PODCAST CARGADOS: ${lstPodcast.count()}-------------------")
        setContentView(R.layout.form_principal_lista_podcast)
        CargarControlesEscuchando()
        val recyclerView = findViewById<RecyclerView>(R.id.Reciclador)
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        recyclerView.setAdapter(Entidad_Podcast_Adapter())
        recyclerView.adapter?.notifyDataSetChanged()

        if(Variables_Globales.isPlaying){
            Variables_Globales.ActualizarEscuchando("Form_Principal_Podcast")
        }

    }

     fun CargarPodcast(){
        dbFirestore.collection("Podcast").get().addOnSuccessListener { documents ->
            for(document in documents){
                var entPodcast = Entidad_Podcast()
                entPodcast.Nombre = document.data["Nombre"].toString()
                entPodcast.ID = Integer.parseInt(document.data["ID"].toString())
                entPodcast.ImagenURL = document.data["ImagenURL"].toString()
                entPodcast.Descripcion = document.data["Descripcion"].toString()
                println("----------------DETALLE @ IDPODCAST: ${entPodcast.ID}  -------------------")
                if(!lstPodcast.contains(entPodcast)){lstPodcast.add(entPodcast)}
                }
            }
        }

    private fun CargarPodcastCapitulos(){
        if(lstPodcast.count() >= 1){
            dbFirestore.collection("Podcast_Capitulos").get().addOnSuccessListener { documents ->
                for(document in documents){
                    var entidadPodcastCapitulo = Entidad_Podcast_Capitulo()
                    entidadPodcastCapitulo.Descripcion = document.data["Descripcion"].toString()
                    entidadPodcastCapitulo.IDCapitulo = Integer.parseInt(document.data["IDCapitulo"].toString())
                    entidadPodcastCapitulo.IDPodcast = Integer.parseInt(document.data["IDPodcast"].toString())
                    entidadPodcastCapitulo.Nombre = document.data["Nombre"].toString()
                    entidadPodcastCapitulo.URL = document.data["URL"].toString()

                    if(!lstPodcast.find { x -> x.ID == entidadPodcastCapitulo.IDPodcast }?.lstCapitulos?.contains(entidadPodcastCapitulo)!!)
                    {lstPodcast.find { x -> x.ID == entidadPodcastCapitulo.IDPodcast }?.lstCapitulos?.add(entidadPodcastCapitulo)}
                }
            }
        }
    }

    private fun CargarControlesEscuchando(){
        imagePodcastEscuchando  =findViewById<ImageView>(R.id.imagenPodcastActual)
        labelNombrePodcastEscuchando  =findViewById<TextView>(R.id.labelNombrePodcastActual)
        labelNombreCapituloEscuchando  =findViewById<TextView>(R.id.labelNombreCapituloActual)
        Variables_Globales.lstBotonesPlayPause.add(findViewById<ImageButton>(R.id.btnPlayStop))
    }

}
