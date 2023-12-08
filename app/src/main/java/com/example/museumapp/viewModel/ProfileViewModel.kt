package com.example.museumapp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.museumapp.model.resources.MuseumAppState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val museumAppState: MuseumAppState
): ViewModel() {

    fun helloworld(){
        Log.d("inject", "hello world")
    }
}