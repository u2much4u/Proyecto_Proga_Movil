package com.example.podcastgeist

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class Form_Principal_Podcast : AppCompatActivity() {

    private var dbFirestore = FirebaseFirestore.getInstance()
    companion object {
        var lstPodcast: MutableList<Entidad_Podcast> = mutableListOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CargarPodcast()
        setContentView(R.layout.form_principal_podcast)
        println("----------------CANTIDAD PODCAST CARGADOS: ${lstPodcast.count()}-------------------")
        val recyclerView = findViewById<RecyclerView>(R.id.Reciclador)
        recyclerView.setAdapter(Entidad_Podcast_Adapter())
        recyclerView.setLayoutManager(LinearLayoutManager(this));

    }

    private fun CargarPodcast(){
        dbFirestore.collection("Podcast").get().addOnSuccessListener { documents ->
            for(document in documents){
                var entPodcast = Entidad_Podcast()
                entPodcast.Nombre = document.data["Nombre"].toString()
                entPodcast.ID = Integer.parseInt(document.data["ID"].toString())
                entPodcast.ImagenURL = document.data["ImagenURL"].toString()
                entPodcast.Descripcion = document.data["Descripcion"].toString()
                lstPodcast.add(entPodcast)
                }
            }
        println(lstPodcast.count().toString())
        println(lstPodcast.size)

        }

}
