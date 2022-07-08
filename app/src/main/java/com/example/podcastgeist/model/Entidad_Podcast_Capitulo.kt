package com.example.podcastgeist.model

data class Entidad_Podcast_Capitulo(
    var IDCapitulo : Int ?= null,
    var IDPodcast : Int ?= null,
    var Nombre : String ?= null,
    var Descripcion: String ?= null,
    var URL: String ?= null
)
