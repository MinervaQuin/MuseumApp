package com.example.museumapp.model.resources

import java.time.LocalDate

data class Work(
    val name: String = "",
    val author: String = "",
    val authorid: String = "",
    val date_of_creation: LocalDate = LocalDate.now(),
    val cover: String = "",
    val description: String = "",
    val temp: Int = 0,
)
