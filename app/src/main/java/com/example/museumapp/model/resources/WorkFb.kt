package com.example.museumapp.model.resources

import com.google.firebase.Timestamp

data class WorkFb(
    val name: String = "",
    val author: String = "",
    val authorid: String = "",
    val date_of_creation: Timestamp,
    val cover: String = "",
    val description: String = ""
)