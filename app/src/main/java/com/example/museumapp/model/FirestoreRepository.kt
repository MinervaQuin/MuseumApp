package com.example.museumapp.model

import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Review
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

interface FirestoreRepository {
    val dataBase: FirebaseFirestore?

    suspend fun getAllArtists(): List<Book?>

}