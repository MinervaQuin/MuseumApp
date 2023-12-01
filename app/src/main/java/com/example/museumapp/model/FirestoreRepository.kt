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
    suspend fun getBook(bookId: String): Book?
    suspend fun getAllBooks(booksIds: List<String>): List<Book?>
    suspend fun getAllBooks2(): List<Book?>

    suspend fun searchAllBooks(allBooks: List<Book?>, searchString: String): List<Book?>

    suspend fun getAuthor(authorId: String) : Author?
    suspend fun getCollection(collectionId: String) : Collection?
    suspend fun getReviews(bookId: String): List<Review?>

    suspend fun upLoadReview(bookId: String, review: Review): Unit

/*
    suspend fun upLoadUserInfo(field: String): Unit

     */
    suspend fun timestampToLocalDate(timestamp: Timestamp): LocalDate
    suspend fun localDateToTimestamp(date: LocalDate?): Timestamp?

    suspend fun uploadBookToFirestore()
    suspend fun addNewAttribute()
    suspend fun addASecondCollection()
    suspend fun deleteReviews()

    suspend fun getReviewsFromABook(bookId: String): List<Review?>
}