package com.example.videoteca.model

import java.io.Serializable

data class Movie (
    val id: Int,
    val name: String,
    val description: String?,
    val year: Int?,
    val cover: String?
) : Serializable