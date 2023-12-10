package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
):ViewModel(){

    var work: Work = Work()
    var autor: Author = Author()

    init {
        work = museumAppState.getWork()
        autor= Author(name = "Francisco De Goya", cover = "https://dbe.rah.es/sites/default/files/styles/wide/public/imagenes/biografias/28608_Goya-y-Lucientes%2C-Francisco-de_0.jpg")
    }
}