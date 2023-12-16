package com.example.museumapp.viewModel

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.MuseumAppState
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class qrViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
    private val firestoreRepository: FirestoreRepository
): ViewModel() {

    private val _isFallo = MutableLiveData<Boolean>()
    val isFallo get() = _isFallo

    private lateinit var navController: NavController
    fun initiateScan(context: Context) {
        val integrator = IntentIntegrator(context as ComponentActivity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Escanea el codigo de barras")
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
    }

    suspend fun handleScanResult(result: String) {
        viewModelScope.launch {
            val work = firestoreRepository.getWork(result)
            if (work != null){
                museumAppState.setWork(work)
                navController.navigate("workView")
                _isFallo.value = false
            }
            else{
                _isFallo.value = true
            }
        }
    }

    fun setNavController(new: NavController){
        navController= new
    }
}
