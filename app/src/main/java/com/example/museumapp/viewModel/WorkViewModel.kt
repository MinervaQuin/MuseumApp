package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
    private val firestoreRepository: FirestoreRepository
):ViewModel(){

    var work: Work = Work()
    var autor: Author? = Author()

    init {
        work = museumAppState.getWork()
        viewModelScope.launch{
            autor = firestoreRepository.geArtist(work.authorid)
        }
    }

    fun setnewAutor(autor : Author){
        museumAppState.setAutor(autor)
    }
}