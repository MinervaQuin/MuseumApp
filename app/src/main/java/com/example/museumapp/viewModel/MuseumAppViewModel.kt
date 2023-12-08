//package com.example.museumapp.viewModel
//
//import androidx.lifecycle.ViewModel
//import com.example.museumapp.model.FirestoreRepository
//import com.example.museumapp.model.resources.MuseumAppState
//import com.example.museumapp.model.resources.User
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//import javax.inject.Inject
//
//@HiltViewModel
//class MuseumAppViewModel @Inject constructor(
//    private val firestoreRepository: FirestoreRepository
//
//
//): ViewModel(){
//    private val _museumState = MutableStateFlow(MuseumAppState())
//    val museumState: StateFlow<MuseumAppState> get() = _museumState
//
//    // Getter y setter corregidos
//    fun getUser(): User? {
//        return _museumState.value.userLoggedIn
//    }
//
//    fun setUser(newUser: User) {
//        _museumState.update { currentState ->
//            currentState.copy(
//                userLoggedIn = newUser
//            )
//        }
//    }
//
//    fun getScannedObras(): Int {
//        return _museumState.value.userLoggedIn?.scanned_works?.size ?: 0
//    }
//
//    fun getTotalObras(): Int {
//        return _museumState.value.totalNumOfWorks
//    }
//
//    suspend fun getUserById(userId: String): User? {
//        return firestoreRepository.getUserById(userId)
//    }
//
//
//}