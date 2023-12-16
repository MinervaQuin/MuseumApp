package com.example.museumapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.AuthorFb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class AuthorsViewModel  @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    val museumAppState: MuseumAppState,

    ): ViewModel(){


    private var allBooks: List<AuthorFb?> = emptyList()

    private val _booksLoaded = MutableLiveData<Boolean>()
    val booksLoaded: LiveData<Boolean> get() = _booksLoaded

    init {
        viewModelScope.launch {

            getAllArtists()
            _booksLoaded.postValue(true)
        }
    }

     suspend fun getAllArtists(): List<AuthorFb?> {
        allBooks= firestoreRepository.getAllArtists()
         return allBooks
    }

}
