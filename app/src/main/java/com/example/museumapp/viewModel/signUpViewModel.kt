package com.example.museumapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.firebaseAuth.EmailAuthUiClient
import com.example.museumapp.model.firebaseAuth.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class signUpViewModel(): ViewModel(){

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private var _navigateToNextScreen = MutableStateFlow(false)
    var navigateToNextScreen = _navigateToNextScreen.asStateFlow()
    // Estado para UI
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _message = MutableStateFlow("")
    val message = _message.asStateFlow()

    private var _showFirstScreen2 = MutableStateFlow(true)
    var showFirstScreen2 = _showFirstScreen2.asStateFlow()

    val emailService by lazy {
        EmailAuthUiClient(auth)
    }

    fun changeScreen(){
        if(_showFirstScreen2.value){
            _showFirstScreen2.value = false
        }else{
            _showFirstScreen2.value = true
        }
        Log.d("Variable publica: ", "${showFirstScreen2.value}")
        Log.d("Variable privada: ", "${_showFirstScreen2.value}")

    }

    /*
    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                auth.createUserWithEmailAndPassword(email, password).await()
                _message.value = "Registro exitoso"
            } catch (e: Exception) {
                _message.value = "Error de registro: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
*/

    fun registerUser(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            val result = emailService.registerUser(email, password)
            _message.value = result.fold(
                onSuccess = {
                    _navigateToNextScreen.value = true // Indica que el registro fue exitoso y se debe navegar
                    it
                },
                onFailure = {
                    "Error de registro: ${it.message}"
                }
            )
            _loading.value = false
        }
    }

}