package com.example.podcastgeist.model

data class Entidad_Podcast (
    var ID : Int ?= null,
    var Nombre : String ?= null,
    var Descripcion: String ?= null,
    var ImagenURL: String ?= null,
    var lstCapitulos: MutableList<Entidad_Podcast_Capitulo> = mutableListOf()
)
