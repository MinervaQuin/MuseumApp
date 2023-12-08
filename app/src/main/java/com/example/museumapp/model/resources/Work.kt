package com.example.museumapp.model.resources

import java.time.LocalDate

data class Work(
    val name: String,
    val author: String ,
    val date_of_creation: LocalDate,
    val cover: String
)
