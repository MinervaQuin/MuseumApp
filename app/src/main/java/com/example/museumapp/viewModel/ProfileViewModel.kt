package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.resources.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository


): ViewModel(){
    private var user: User? = null

    // Getter y setter corregidos
    fun getUser(): User? {
        return user
    }

    fun setUser(newUser: User) {
        user = newUser
    }

    fun getScannedObras(): Int {
        return user?.scanned_works?.size ?: 0
    }

    fun getTotalObras(): Int {
        return 124
    }

    suspend fun getUserById(userId: String): User? {
        return firestoreRepository.getUserById(userId)
    }


}