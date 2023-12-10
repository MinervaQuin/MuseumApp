package com.example.museumapp.model

import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

interface FirestoreRepository {
    val dataBase: FirebaseFirestore?

    suspend fun getAllArtists(): List<Book?>

    suspend fun getUserById(userId: String): User?

    suspend fun uploadWorktoFirestore()

    suspend fun timestampToLocalDate(timestamp: Timestamp): LocalDate
    suspend fun convertLocalDateToTimestamp(localDate: LocalDate): Timestamp

}