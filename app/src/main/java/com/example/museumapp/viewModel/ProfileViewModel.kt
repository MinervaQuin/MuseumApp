package com.example.museumapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.MuseumAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
    // Otros inyectables si es necesario
) : ViewModel() {

    private val _userLoaded = MutableLiveData<Boolean>()
    val userLoaded: LiveData<Boolean> get() = _userLoaded

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val user = museumAppState.getUserById()
                if (user != null) {
                    museumAppState.setUser(user)
                    _userLoaded.postValue(true)
                } else {
                    _userLoaded.postValue(false)
                }
            } catch (e: Exception) {
                _userLoaded.postValue(false)
                Log.e("Firestore", "Error en ProfileViewModel", e)
            }
        }
    }

    // Otras funciones del ViewModel
}
