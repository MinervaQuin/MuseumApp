package com.example.museumapp.model.resources

import com.example.museumapp.model.FirestoreRepository
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MuseumAppState @Inject constructor(
    val firestoreRepository: FirestoreRepository
){
    private var userLoggedIn: User? = null
    private val userId: String = "9gQ3UeEIwX6vARlg2lhT"
    private val totalNumOfWorks: Int = 0

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

}