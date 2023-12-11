package com.example.museumapp.model

import androidx.navigation.NavHostController
import com.example.museumapp.model.resources.User
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Work
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MuseumAppState @Inject constructor(
    val firestoreRepository: FirestoreRepository
){
    private var userLoggedIn: User? = null
    private val userId: String = "9gQ3UeEIwX6vARlg2lhT"
    private val totalNumOfWorks: Int = 0
    private var coleccion: Collection = Collection()
    private var work: Work = Work()
    private lateinit var navController: NavHostController


    fun setcoleccion(newColecction: Collection) {
        coleccion = newColecction

    }
    fun getcoleccion(): Collection {
        return coleccion
    }

    fun setWork(newWork: Work) {
        work = newWork
    }
    fun getWork(): Work {
        return work
    }


    fun getUser(): User? {
        return userLoggedIn
    }

    fun setUser(newUser: User) {
        userLoggedIn = newUser

    }

    fun getScannedObras(): Int {
        return userLoggedIn?.scanned_works?.size ?: 0
    }

    fun getTotalObras(): Int {
        return totalNumOfWorks
    }

    suspend fun getUserById(): User? {
        return firestoreRepository.getUserById(userId)
    }

//    suspend fun getUserLogged() : UserData? {
//        return firestoreRepository.getUser
//    }

    fun getUserId(): String {

        return userId
    }

}