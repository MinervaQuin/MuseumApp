package com.example.museumapp.model

import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.AuthorFb
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.SuperCollection
import com.example.museumapp.model.resources.User
import com.example.museumapp.model.resources.Work
import com.example.museumapp.model.resources.WorkFb
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate

interface FirestoreRepository {
    val dataBase: FirebaseFirestore?

    suspend fun getAllArtists(): List<AuthorFb?>
    suspend fun getAllWorks(): List<Work?>

    suspend fun getUserById(userId: String): User?

    suspend fun uploadWorktoFirestore()

    suspend fun timestampToLocalDate(timestamp: Timestamp): LocalDate
    suspend fun convertLocalDateToTimestamp(localDate: LocalDate): Timestamp
    suspend fun getWork(workId: String): Work?
    suspend fun getAllWorks(worksIds: List<String>): List<Work?>
    suspend fun getCollection(collectionId: String) : Collection?
    suspend fun getAllCollections(collectionsIds: List<String>): List<Collection?>
    suspend fun getSuperCollection(superCollectionId: String) : SuperCollection?
    suspend fun getAllSuperCollections(): List<SuperCollection?>
    suspend fun geArtist(artistId: String): Author?

}