package com.example.museumapp.model.resources

data class User (
    val name: String = "",
    val email: String = "",
    val photo: String = "",
    var scanned_works: Array<String> = arrayOf()

    )