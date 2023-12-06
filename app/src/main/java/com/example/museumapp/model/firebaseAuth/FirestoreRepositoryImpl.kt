package com.example.museumapp.model.firebaseAuth

import android.util.Log
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.AuthorFb
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Review
import com.example.museumapp.model.resources.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.regex.Pattern
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random


class FirestoreRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): FirestoreRepository {
    override val dataBase: FirebaseFirestore?
        get() = firebaseFirestore


//    override suspend fun getAllBooks(booksIds: List<String>): List<Book?> {
//        val bookArray: MutableList<Book?> = mutableListOf()
//        return try {
//            booksIds.forEach { bookId ->
//                val book = getBook(bookId)
//                if(book != null){
//                    bookArray.add(book)
//                }
//            }
//            bookArray
//        } catch (e: Exception) {
//            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
//            emptyList()
//        }
//    }

    override suspend fun getAllArtists(): List<Book?> {
        val bookArray: MutableList<Book?> = mutableListOf()
        return try {
            val querySnapshot = FirebaseFirestore.getInstance()
                .collection("artists") // Reemplaza con el nombre de tu colección en Firestore
                .get()
                .await()

            for (document in querySnapshot.documents) {
                document.getString("name")?.let { Log.d("Firestore", it) }

            }
            Log.d("Firestore", bookArray.size.toString())
            bookArray
        } catch (e: Exception) {
            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
            emptyList()
        }
    }

    override suspend fun getUserById(userId: String): User? {
        val firestore = FirebaseFirestore.getInstance()
        val userCollection = firestore.collection("users")

        try {
            val documentSnapshot: DocumentSnapshot = userCollection.document(userId).get().await()

            if (documentSnapshot.exists()) {
                val userData = documentSnapshot.data

                // Extraer datos del documento y crear un objeto User
                val name = userData?.get("name") as? String ?: ""
                val email = userData?.get("email") as? String ?: ""
                val photo = userData?.get("photo") as? String ?: ""

                // Aquí asumimos que "scanned_works" es un campo de tipo Array en Firestore
                val scannedWorks = (userData?.get("scanned_works") as? List<*>)?.map { it.toString() }?.toTypedArray()
                    ?: arrayOf()

                return User(name, email, photo, scannedWorks)
            }
        } catch (e: Exception) {
            // Manejar excepciones, por ejemplo, problemas de red
            e.printStackTrace()
        }

        return null
    }


//    override suspend fun searchAllBooks(allBooks: List<Book?>, searchString: String): List<Book?> {
//        val bookArray: MutableList<Book?> = mutableListOf()
//
//        return try {
//            val regexPattern: String = "$searchString.*$"
//            val pattern: Pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE)
//
//            allBooks.forEach { book ->
//                if (isBookMatching(book, pattern)) {
//                    Log.d("FirestoreRepository", "Coincidencia encontrada")
//                    bookArray.add(book)
//                } else {
//                    Log.d("FirestoreRepository", "No se encontró coincidencia")
//                }
//            }
//            bookArray
//
//
//            return bookArray
//        } catch (e: Exception) {
//            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
//            emptyList()
//        }
//    }





}