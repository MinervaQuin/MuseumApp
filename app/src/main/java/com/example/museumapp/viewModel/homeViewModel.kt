package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class homeViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository

): ViewModel(){

suspend fun getAllArtists(){
    firestoreRepository.getAllArtists()
}
}