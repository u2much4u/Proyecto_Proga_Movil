package com.example.podcastgeist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class Form_Principal_Lista_Podcast : AppCompatActivity() {

    private var dbFirestore = FirebaseFirestore.getInstance()
    companion object {
        var lstPodcast: MutableList<Entidad_Podcast> = mutableListOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CargarPodcast()
        println("----------------CANTIDAD PODCAST CARGADOS: ${lstPodcast.count()}-------------------")
        if(lstPodcast.isEmpty()){CargarPodcast()}
        setContentView(R.layout.form_principal_lista_podcast)
        println("----------------CANTIDAD PODCAST CARGADOS2: ${lstPodcast.count()}-------------------")
        val recyclerView = findViewById<RecyclerView>(R.id.Reciclador)
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        recyclerView.setAdapter(Entidad_Podcast_Adapter())
        recyclerView.adapter?.notifyDataSetChanged()

    }

    private fun CargarPodcast(){
        dbFirestore.collection("Podcast").get().addOnSuccessListener { documents ->
            for(document in documents){
                var entPodcast = Entidad_Podcast()
                entPodcast.Nombre = document.data["Nombre"].toString()
                entPodcast.ID = Integer.parseInt(document.data["ID"].toString())
                entPodcast.ImagenURL = document.data["ImagenURL"].toString()
                entPodcast.Descripcion = document.data["Descripcion"].toString()
                if(!lstPodcast.contains(entPodcast)){lstPodcast.add(entPodcast)}
                }
            }
        }




}
