package com.example.museumapp.viewModel

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.MuseumAppState
import com.google.zxing.integration.android.IntentIntegrator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class qrViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
): ViewModel() {

    private val _isFallo = MutableLiveData<Boolean>()
    val isFallo get() = _isFallo
    fun initiateScan(context: Context) {
        val integrator = IntentIntegrator(context as ComponentActivity)
        integrator.setPrompt("Escanea el codigo de barras")
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
    }

    suspend fun handleScanResult(result: String) {
        viewModelScope.launch {
            _isFallo.value = false
        }
    }
}
