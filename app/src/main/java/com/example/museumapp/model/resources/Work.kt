package com.example.museumapp.model.resources

import java.time.LocalDate

data class Work(
    var name: String = "",
    var author: String = "",
    var authorid: String = "",
    var date_of_creation: LocalDate = LocalDate.now(),
    var cover: String = "",
    var description: String = "",
    var temp: Int = 0,
)
