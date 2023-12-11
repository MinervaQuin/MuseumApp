package com.example.museumapp.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.SuperCollection
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class coleccionesViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
    private val firestoreRepository: FirestoreRepository
): ViewModel()  {
    var colecciones by mutableStateOf(listOf<SuperCollection?>())
        private set

    private var _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()

    init {

        viewModelScope.launch{
            _loading.value = true
            colecciones = firestoreRepository.getAllSuperCollections()
            _loading.value = false
        }

    }

    fun setcolection(coleccion: Collection){
        museumAppState.setcoleccion(coleccion)
    }
}
