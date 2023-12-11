package com.example.museumapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.MuseumAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Work
import kotlinx.coroutines.launch

@HiltViewModel
class coleccionViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
    private val firestoreRepository: FirestoreRepository
) : ViewModel()  {
    var coleccion: Collection = Collection()
    var introduccion by mutableStateOf<Boolean>(true)
        private set

    init {
        coleccion = museumAppState.getcoleccion()
    }

    fun setWork(work : Work){
        museumAppState.setWork(work)
    }

    fun setIntro(new : Boolean){
        /*viewModelScope.launch{
            firestoreRepository.uploadWorktoFirestore()
        }

         */
        introduccion=new
    }
}