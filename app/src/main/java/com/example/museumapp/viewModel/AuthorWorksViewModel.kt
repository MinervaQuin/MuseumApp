package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Work
import javax.inject.Inject

class AuthorWorksViewModel @Inject constructor(
): ViewModel(){
    var autor: Author = Author()
    init {
        val worksArray: Array<Work?> = arrayOf(
            Work(name = "La reina María Luisa con mantilla", temp = 1800, cover = "https://content3.cdnprado.net/imagenes/Documentos/imgsem/11/1149/1149fdbc-6ee9-4298-8de9-1ad9467dee98/73e326b5-8e13-4b61-9e05-38f14c36b1db.jpg" ),
            Work(name = "La reina María Luisa con mantilla", temp = 1800, cover = "https://content3.cdnprado.net/imagenes/Documentos/imgsem/11/1149/1149fdbc-6ee9-4298-8de9-1ad9467dee98/73e326b5-8e13-4b61-9e05-38f14c36b1db.jpg" ),
            Work(name = "La reina María Luisa con mantilla", temp = 1800, cover = "https://content3.cdnprado.net/imagenes/Documentos/imgsem/11/1149/1149fdbc-6ee9-4298-8de9-1ad9467dee98/73e326b5-8e13-4b61-9e05-38f14c36b1db.jpg" )
            // Agrega más obras según sea necesario
        )
        autor= Author(name = "Agustín Esteve y Marqués", cover = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/Augustin_Esteve_presumed_self_portrait.jpg/220px-Augustin_Esteve_presumed_self_portrait.jpg", type = "Escuela Española",
            place_Birth_and_Dead = "Valencia 1753-1820", works = worksArray)
    }
}