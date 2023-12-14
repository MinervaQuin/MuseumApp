package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorWorksViewModel @Inject constructor(
    val museumAppState: MuseumAppState
): ViewModel(){
    var autor: Author = Author()
    init {
//        val worksArray: Array<Work?> = museumAppState.getAutor().works
        val worksArray: Array<Work?> = arrayOf(
            Work(name = "El Aquelarre", temp = 1814, cover = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/GOYA_-_El_aquelarre_%28Museo_L%C3%A1zaro_Galdiano%2C_Madrid%2C_1797-98%29.jpg/1280px-GOYA_-_El_aquelarre_%28Museo_L%C3%A1zaro_Galdiano%2C_Madrid%2C_1797-98%29.jpg" ),
            Work(name = "Saturno Devorando a su hijo", temp = 1832, cover = "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg" ),
            // Agrega más obras según sea necesario
        )
        autor= Author(name = "Francisco de Goya"
            , cover = "https://dbe.rah.es/sites/default/files/styles/wide/public/imagenes/biografias/28608_Goya-y-Lucientes%2C-Francisco-de_0.jpg", type = "Escuela Española",
            place_Birth_and_Dead = "30 de marzo de 1746, Fuendetodos", works = worksArray)
    }
}