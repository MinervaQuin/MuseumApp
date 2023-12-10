package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.MuseumAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.museumapp.model.resources.Collection

@HiltViewModel
class coleccionViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
) : ViewModel()  {
    var coleccion: Collection = Collection()

    init {
        coleccion = museumAppState.getcoleccion()
    }
}